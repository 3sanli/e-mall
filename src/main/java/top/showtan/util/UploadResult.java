package top.showtan.util;

public class UploadResult {
    private Integer error;
    private String url;
    private String message;

    public UploadResult() {
    }

    public UploadResult(String url){
        this.error = 0;
        this.url = url;
    }

    public static UploadResult SUCCESS(String url){
        return new UploadResult(url);
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
