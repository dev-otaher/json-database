package server.request;

public class Request {
    private RequestType type;
    private String key;
    private String value;

    public Request(RequestType type, String key, String value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public RequestType getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }


    @Override
    public String toString() {
        return "Request{" +
                "type=" + type +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
