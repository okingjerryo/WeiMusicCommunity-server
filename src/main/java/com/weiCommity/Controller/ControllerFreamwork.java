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

    public static ResponseEntity<HttpJson> execute(String jsonString, String outClassName, ControllerFuntion funtion) {
        inObj = new HttpJson(jsonString);
        testClassName = outClassName;
        return doingthings(funtion);
    }

    public static ResponseEntity<HttpJson> excecute(String jsonString, Class className, String outClassName, ControllerFuntion funtion) {
        inObj = new HttpJson(jsonString, className);
        testClassName = outClassName;
        return doingthings(funtion);
    }

    public static ResponseEntity<HttpJson> excecute(String jsonString, TypeReference typeReference, String testClassName, ControllerFuntion funtion) {
        inObj = new HttpJson(jsonString, typeReference);
        return doingthings(funtion);
    }

    private static ResponseEntity<HttpJson> doingthings(ControllerFuntion funtion) {
        re = new HttpJson();
        try {
            if (!inObj.getClassName().equals(testClassName))
                throw new JSONException("");
            funtion.thisControllerDoing(inObj, re);
            re.constractJsonString();
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("请求不合法" + e.getMessage());
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            re.constractJsonString();
            return new ResponseEntity<HttpJson>(re, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<HttpJson>(re, HttpStatus.OK);
    }

}
