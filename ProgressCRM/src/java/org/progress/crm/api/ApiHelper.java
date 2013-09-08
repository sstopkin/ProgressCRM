package org.progress.crm.api;

import javax.ws.rs.core.Response;

class ApiHelper {

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
}
