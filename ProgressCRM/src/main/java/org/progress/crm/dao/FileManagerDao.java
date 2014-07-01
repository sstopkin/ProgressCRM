package org.progress.crm.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.progress.crm.logic.Constants;

public class FileManagerDao {

    public File getFileByPath(String path) {
        File result = new File(Constants.SETTINGS.BASEPATH + "//" + path);
        return result;
    }

    public InputStream getFileInputStreamByPath(String path) throws Exception {
        FileInputStream fileIs = new FileInputStream(Constants.SETTINGS.BASEPATH + "//" + path);
        return fileIs;
    }

//      @GET
//  @Path("/file")
//  @Produces("image/png")
//  public File getFile(){
//    File samplePDF = new File("c:/temp/distributedmap.png");
//    return samplePDF;
//  }
//
//  @GET
//  @Path("/fileis")
//  @Produces("image/png")
//  public InputStream getFileInputStream()throws Exception{
//    FileInputStream fileIs = new FileInputStream("c:/temp/distributedmap.png");
//    return fileIs;
//  }
//
//  @GET
//  @Path("/fileso")
//  @Produces("image/png")
//  public StreamingOutput getFileStreamingOutput() throws Exception{
//
//    return new StreamingOutput() {
//
//      @Override
//      public void write(OutputStream outputStream) throws IOException,
//          WebApplicationException {
//        FileInputStream inputStream = new FileInputStream("c:/temp/distributedmap.png");
//        int nextByte = 0;
//        while((nextByte  = inputStream.read()) != -1 ){
//          outputStream.write(nextByte);
//        }
//        outputStream.flush();
//        outputStream.close();
//        inputStream.close();
//      }
//    };
//  }
    public boolean mkDir(String path) {
        return (new File(Constants.SETTINGS.BASEPATH + path)).mkdir();
    }

    public boolean removeFile(String path) {
        String[] parts = path.replaceAll("\"", "").split(",");
        List<String> wordList = Arrays.asList(parts);
        for (String f : wordList) {
            File file = new File(Constants.SETTINGS.BASEPATH + f);
            try {
                delete(file);
            } catch (IOException ex) {
                Logger.getLogger(FileManagerDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!file.exists()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }
        }
        return true;
    }

    void delete(File f) throws IOException {
        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                delete(c);
            }
        }
        if (!f.delete()) {
            throw new FileNotFoundException("Failed to delete file: " + f);
        }
    }

    class CustomFile {

        private final String name;
        private final String path;
        private final String modifyTime;
        private final String size;
        private final boolean isFile;

        public CustomFile(String name, String path, String modifyTime, String size, boolean isFile) {
            this.name = name;
            this.path = path;
            this.modifyTime = modifyTime;
            this.size = size;
            this.isFile = isFile;
        }
    }

    public List getFolderFileList(String path) {
        // Directory path here
        File folder = new File(Constants.SETTINGS.BASEPATH + path);
        File[] listOfFiles = folder.listFiles();
        List result = new ArrayList();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                String lastMfDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date(folder.
                        lastModified()));
                result.add(new CustomFile(file.getName(), file.getPath().replace(Constants.SETTINGS.BASEPATH, ""),
                        lastMfDate, String.valueOf(file.length()), file.isFile()));
            }
        }
        return result;
    }
}
