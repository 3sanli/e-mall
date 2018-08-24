package top.showtan.util;

public final class Response {
    private String code;
    private String message;

    private Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Response SUCCESS() {
        return new Response("1000", "SUCCESS");
    }

    public static Response SUCCESS(String message) {
        return new Response("1000", message);
    }

    public static Response ERROR() {
        return new Response("500", "ERROR");
    }

    public static Response ERROR(String message) {
        return new Response("500", message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
