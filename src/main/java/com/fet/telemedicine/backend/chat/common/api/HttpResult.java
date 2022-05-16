package com.fet.telemedicine.backend.chat.common.api;

public class HttpResult<T> {
    
    private long code;
    private String message;
    private T data;

    protected HttpResult() {
    }

    protected HttpResult(long code, String message, T data) {
	this.code = code;
	this.message = message;
	this.data = data;
    }

    public static <T> HttpResult<T> success(T data) {
	return new HttpResult<T>(HttpStatus.SUCCESS.getCode(), HttpStatus.SUCCESS.getMessage(), data);
    }

    public static <T> HttpResult<T> success(T data, String message) {
	return new HttpResult<T>(HttpStatus.SUCCESS.getCode(), message, data);
    }

    public static <T> HttpResult<T> failed(HttpStatus errorCode) {
	return new HttpResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static <T> HttpResult<T> failed(String message) {
	return new HttpResult<T>(HttpStatus.FAILED.getCode(), message, null);
    }

    public static <T> HttpResult<T> failed() {
	return failed(HttpStatus.FAILED);
    }

    public static <T> HttpResult<T> validateFailed() {
	return failed(HttpStatus.VALIDATE_FAILED);
    }

    public static <T> HttpResult<T> validateFailed(String message) {
	return new HttpResult<T>(HttpStatus.VALIDATE_FAILED.getCode(), message, null);
    }

    public static <T> HttpResult<T> unauthorized(T data) {
	return new HttpResult<T>(HttpStatus.UNAUTHORIZED.getCode(), HttpStatus.UNAUTHORIZED.getMessage(), data);
    }

    public static <T> HttpResult<T> forbidden(T data) {
	return new HttpResult<T>(HttpStatus.FORBIDDEN.getCode(), HttpStatus.FORBIDDEN.getMessage(), data);
    }

    public long getCode() {
	return code;
    }

    public void setCode(long code) {
	this.code = code;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public T getData() {
	return data;
    }

    public void setData(T data) {
	this.data = data;
    }
}
