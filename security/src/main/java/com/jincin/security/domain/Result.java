package com.jincin.security.domain;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by wang on 2017/5/23.
 */
public class Result<T> {

    private String errorCode;

    private String errorInfo;

    private T data;

    private boolean success;

    public Result() {

    }

    public Result(String errorCode, String errorInfo, T data, boolean success) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
        this.data = data;
        this.success = success;
    }

    public Object mapToObject(Object target) {
        try {
            Field[] fields = target.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String key = field.getName();

                Map<String, Object> map = (Map<String, Object>) data;
                if (null != map.get(key)) {
                    field.set(target, map.get(key));
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return target;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
