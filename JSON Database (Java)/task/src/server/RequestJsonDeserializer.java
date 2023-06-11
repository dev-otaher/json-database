package server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import server.request.Request;
import server.request.RequestType;

import java.lang.reflect.Type;

public class RequestJsonDeserializer implements JsonDeserializer<Request> {
    @Override
    public Request deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String typeAsString = json.getAsJsonObject().get("type").getAsString().toUpperCase();
        RequestType requestType = RequestType.valueOf(typeAsString);
        JsonElement keyElement = json.getAsJsonObject().get("key");
        String key = null;
        if (keyElement != null) {
            key = keyElement.getAsString();
        }
        JsonElement valueElement = json.getAsJsonObject().get("value");
        String value = null;
        if (valueElement != null) {
            value = valueElement.getAsString();
        }
        return new Request(requestType, key, value);
    }
}
