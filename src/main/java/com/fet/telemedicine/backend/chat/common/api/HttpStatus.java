package com.fet.telemedicine.backend.chat.common.api;

 
public enum HttpStatus  {
    
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失敗"),
    VALIDATE_FAILED(404, "參數檢驗失敗"),
    UNAUTHORIZED(401, "尚未登入或Token已過期"),
    FORBIDDEN(403, "沒有相關權限");
    
    private long code;
    private String message;

    private HttpStatus(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
