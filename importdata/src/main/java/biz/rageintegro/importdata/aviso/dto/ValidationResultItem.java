package biz.rageintegro.importdata.aviso.dto;

public class ValidationResultItem {
    private String item;
    private String message;

    public ValidationResultItem() {
    }

    public ValidationResultItem(String item, String message) {
        this.item = item;
        this.message = message;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
