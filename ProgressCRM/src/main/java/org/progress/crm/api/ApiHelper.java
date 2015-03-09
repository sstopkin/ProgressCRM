package org.progress.crm.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.core.Response;

public class ApiHelper {

    public static Response getResponse(boolean value) {
        return Response.ok(String.valueOf(value)).build();
    }

    public static Response getResponse(int value) {
        return Response.ok(String.valueOf(value)).build();
    }

    public static Response getResponse(String text) {
        if (text == null) {
            return getResponse(false);
        }
        return Response.ok(text).build();
    }

    public static Response getResponse(Object f) {
        Response.ResponseBuilder response = Response.ok((Object) f);
        response.header("Content-Disposition", "attachment; filename=\"" + ((File) f).getName() + "\"");
        return response.build();
    }

    public static Response getResponse(File f) {
        Response.ResponseBuilder response = Response.ok((Object) f);
        return response.build();
    }

    public static Response getResponse(Exception ex) {
        return Response.ok(ex.getMessage()).status(500).build();
    }

    public static JsonSerializer<Date> ser = new JsonSerializer<Date>() {
        @Override
        public JsonElement serialize(Date t, java.lang.reflect.Type type, JsonSerializationContext jsc) {
            return t == null ? null : new JsonPrimitive(new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss").format(t));
        }
    };

    public static JsonDeserializer<Date> deser = new JsonDeserializer<Date>() {
        @Override
        public Date deserialize(JsonElement je, java.lang.reflect.Type type, JsonDeserializationContext jdc) throws JsonParseException {
            return je == null ? null : new Date(je.getAsLong());
        }
    };

}
