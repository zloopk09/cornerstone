package top.zloop.mobile.lib.utils.httpstatuscode;

public class StatusCode {
    private int code;
    private String text;
    private String type;
    private String description;

    public StatusCode(int code, String text, String type, String description) {
        this.code = code;
        this.text = text;
        this.type = type;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toTypeAndText(){
        return type+": "+text;
    }

    @Override
    public String toString() {
        return "StatusCode{" +
                "Code=" + code +
                ", Type='" + type + '\'' +
                ", Text='" + text + '\'' +
                ", Description='" + description + '\'' +
                '}';
    }
}
