package org.progress.crm.exceptions;

import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<CustomException>{

    @Override
    public Response toResponse(CustomException exception) {
        ResponseBuilderImpl response = new ResponseBuilderImpl();
        response.entity(exception.getMessage());
        response.status(Response.Status.BAD_REQUEST);
        return response.build();
    }
}
