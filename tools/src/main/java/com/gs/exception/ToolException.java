package com.gs.exception;

/**
 * ...
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/07/08 19:34
 **/
public class ToolException extends RuntimeException {
    private String code;

    public ToolException() {
        super();
    }

    public ToolException(String code) {
        super();
        this.code = code;
    }

    public ToolException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ToolException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ToolException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
