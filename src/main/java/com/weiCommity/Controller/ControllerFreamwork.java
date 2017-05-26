package com.weiCommity.Controller;

import com.alibaba.fastjson.TypeReference;
import com.weiCommity.Util.HttpJson;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
 * PackageName com.weiCommity.Controller
 * Created by uryuo on 17/5/12.
 */
public final class ControllerFreamwork {
    interface ControllerFuntion {
        HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception;
    }

    protected static String testClassName;
    protected static HttpJson inObj;
    protected static HttpJson re;
    private Class className;
    private TypeReference typeReference;

    //无携带类
    public static ResponseEntity<HttpJson> execute(String jsonString, String outClassName, ControllerFuntion funtion) {
        inObj = new HttpJson(jsonString);
        testClassName = outClassName;
        return doingthings(funtion);
    }
    //携带类为普通Model类
    public static ResponseEntity<HttpJson> excecute(String jsonString, Class className, String outClassName, ControllerFuntion funtion) {
        inObj = new HttpJson(jsonString, className);
        testClassName = outClassName;
        return doingthings(funtion);
    }
    //携带类为泛型集合类
    public static ResponseEntity<HttpJson> excecute(String jsonString, TypeReference typeReference, String testClassName, ControllerFuntion funtion) {
        inObj = new HttpJson(jsonString, typeReference);
        return doingthings(funtion);
    }

    private static ResponseEntity<HttpJson> doingthings(ControllerFuntion funtion) {
        //新建一个给服务端发送的Json类
        re = new HttpJson();
        try {
            //对不属于本Controller的请求进行排他
            if (!inObj.getClassName().equals(testClassName))
                throw new JSONException("");
            //执行每个Controller特有的逻辑
            funtion.thisControllerDoing(inObj, re);
            //Controller执行结束后re发送端的json串进行组装
            re.constractJsonString();
            //请求不合法抛出异常
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("请求不合法" + e.getMessage());
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
            //服务器抛出异常
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            re.constractJsonString();
            return new ResponseEntity<HttpJson>(re, HttpStatus.BAD_GATEWAY);
        }
        //没有抛出异常则将数据发送给客户端
        return new ResponseEntity<HttpJson>(re, HttpStatus.OK);
    }

}
