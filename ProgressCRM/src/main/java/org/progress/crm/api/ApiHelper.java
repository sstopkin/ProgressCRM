package org.progress.crm.api;

import java.io.File;
import javax.ws.rs.core.Response;
import org.progress.crm.exceptions.BadRequestException;
import org.progress.crm.exceptions.CustomException;

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

    public static Response getResponse(File f) {
        Response.ResponseBuilder response = Response.ok((File) f);
        response.header("Content-Disposition", "attachment; filename=\"" + f.getName() + "\"");
        return response.build();
    }

    public static Response getResponse(Exception ex) {
        return Response.ok(ex.getMessage()).build();
    }

    public static int parseInt(String value) throws CustomException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            throw new BadRequestException();
        }
    }
}
