package com.jincin.provider.util;

/**
 * Created by wang on 2017/5/23.
 */
public class ResultBuilder {

    public static Result success() {
        return new Result("1", "SUCCESS", "success", true);
    }

    public static Result success(Object data) {
        return new Result("1", "SUCCESS", data, true);
    }

    public static Result error(String errorInfo) {
        return new Result("0", errorInfo, "", false);
    }

    public static Result error(String errorCode, String errorInfo) {
        return new Result(errorCode, errorInfo, "", false);
    }

    public static Result error(String errorCode, String errorInfo, Object data) {
        return new Result(errorCode, errorInfo, data, false);
    }
}
