package nju.ycqian.stockservice.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Date;

@Slf4j
public class ObjectJsonConverter {
    public static JSONObject parseObject(Object object) {
        JSONObject jsonObject = new JSONObject();
        Field[] fields = object.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getType().equals(Date.class)) {
                    //把Date类型的属性转化为时间戳
                    jsonObject.put(field.getName(), ((Date) field.get(object)).getTime());
                } else {
                    jsonObject.put(field.getName(), field.get(object));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return jsonObject;
    }

    public static JSONObject merge(JSONObject jsonObject, Object object) {
        if (jsonObject == null) {
            return null;
        }
        Field[] fields = object.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getType().equals(Date.class)) {
                    jsonObject.put(field.getName(), ((Date) field.get(object)).getTime());
                } else {
                    jsonObject.put(field.getName(), field.get(object));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return jsonObject;
    }
}