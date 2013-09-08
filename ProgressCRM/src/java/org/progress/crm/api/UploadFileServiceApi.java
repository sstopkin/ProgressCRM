package org.progress.crm.api;

import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.FileNotFoundException;
import java.nio.charset.CharacterCodingException;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.hibernate.Session;
import org.progress.crm.controllers.UploadController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("fileupload")
public class UploadFileServiceApi {

    @EJB
    UploadController uploadController;

    @POST
    @Path("photo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadBinaryFile(
            @FormDataParam("file") final InputStream uploadedInputStream,
            @FormDataParam("file") final FormDataContentDisposition fileDetail)
            throws CharacterCodingException, IOException, FileNotFoundException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException, FileNotFoundException, IOException {
                String resp = uploadController.addPhoto(session, uploadedInputStream,
                        fileDetail);
                return ApiHelper.getResponse(resp);
            }
        });
    }

    @POST
    @Path("text")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadTextFile(
            @FormDataParam("file") final InputStream uploadedInputStream,
            @FormDataParam("file") final FormDataContentDisposition fileDetail)
            throws CharacterCodingException, IOException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException, IOException {
                String resp = uploadController.getUploadedFileContent(session, uploadedInputStream,
                        fileDetail);
                return ApiHelper.getResponse(resp);
            }
        });
    }
}