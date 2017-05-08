package com.weiCommity.Util;

/**
 * Created by Emily on 2017/4/14.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.joda.time.LocalDate;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * 描述：服务器封装数据传输用的类，通过这个类不同的客户端可以相互传递数据
 * 发送时：
 *      1.装配需要的变量（如果不修改默认是不发送任何类+传递正常的信息）
 *      2.调用 constractJsonString() 进行装配JsonString
 *      3.通过Servlet发送json报文
 *
 * 接收时：
 *      1.使用bool 变量来检查 resolveJsnString() 方法的结果
 *      2.无 Object需求 直接获取信息即可
 *      3.若要获取Object 先检查getClassName 看看与目标类是否符合
 *      4.符合再取调用 getClassObject 取Object，不符合请重发请求
 * Created by uryuo on 17/4/6
 */

public class HttpJson {
    private int statusCode = 400;   //默认传递正常
    private String Message = "OK";   //默认消息是无异常
    private String className = ""; //默认没有类
    private Object classObject = null;
    private JSONObject jsonObject = new JSONObject();

    //为了支持扩展度极差的fastJson 用的String
    private String classObjectString;


    private String jsonString = ""; //json格式的串
    //封装Json
    public void constractJsonString(){

        try {
            jsonObject.put("statusCode", statusCode);
            jsonObject.put("message", Message);
            jsonObject.put("className", className);
            //用中间服务将Object转成String
            //SerializeConfig.getGlobalInstance().put(Timestamp.class, new TimestampSerializer());
            classObjectString = JSON.toJSONString(classObject, SerializerFeature.WriteDateUseDateFormat);
            jsonObject.put("classObjectString", classObjectString);
            jsonString = jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public HttpJson() {
        super();
    }

    public boolean resolveJsonString() throws JSONException {
        boolean re = false;
        jsonObject = new JSONObject(jsonString);
        try{
            this.statusCode = jsonObject.getInt("statusCode");
            this.Message = jsonObject.getString("message");
            this.className = jsonObject.getString("className");
            re = true;
        }catch (Exception e){
            System.out.println("json 解析出错");
            e.printStackTrace();
        } finally {
            return re;
        }
    }

    public boolean resolveJsonString(Class className) {
        boolean re = false;
        try {
            re = resolveJsonString();
            ParserConfig.getGlobalInstance().putDeserializer(LocalDate.class, JodaTimeDeserializer.instance);
            this.classObjectString = jsonObject.getString("classObjectString");
            if (!(classObjectString.equals("") || classObjectString.equals("{}"))) {
                this.classObject = JSON.parseObject(classObjectString, className, JSON.DEFAULT_PARSER_FEATURE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return re;

    }

    public boolean resolveJsonStrngTR(TypeReference typeReference) {

        try {
            resolveJsonString();
            ParserConfig.getGlobalInstance().putDeserializer(LocalDate.class, JodaTimeDeserializer.instance);
            this.classObjectString = jsonObject.getString("classObjectString");
            if (!(classObjectString.equals("") || classObjectString.equals("null") || classObjectString == null)) {
                this.classObject = JSON.parseObject(classObjectString, typeReference);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void setPara(String key, String elem) throws JSONException {
        jsonObject.put(key,elem);
    }

    public String getPara(String key) throws JSONException {
        return jsonObject.getString(key);
    }


    public HttpJson(String jsonString) {
        super();
        this.jsonString = jsonString;
        try {
            this.resolveJsonString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public HttpJson(String jsonString, Class classname) throws JSONException {
        super();
        this.jsonString = jsonString;
        this.resolveJsonString(classname);
    }

    public HttpJson(String jsonString, TypeReference typeReference) {
        super();
        this.jsonString = jsonString;
        this.resolveJsonStrngTR(typeReference);
    }

    //getter & setter
    public String getJsonString(){
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className){
        this.className = className;
    }

    public Object getClassObject() {
        return classObject;
    }

    public void setClassObject(Object classObject) {
        this.classObject = classObject;
    }



}

