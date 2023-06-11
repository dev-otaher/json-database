package server.response;

public class OkResponse extends Response{
    private String value;

    public OkResponse(String value) {
        super("OK");
        this.value = value;
    }
}
