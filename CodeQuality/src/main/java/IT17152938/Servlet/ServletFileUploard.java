package IT17152938.Servlet;

import IT17152938.Contoller.QualityController;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class ServletFileUploard extends javax.servlet.http.HttpServlet {




    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {




        // Check that we have a file upload request
        PrintWriter out = response.getWriter();
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    // ... (do your job here)
                } else {
                    // Process form file field (input type="file").
                    String fieldName = item.getFieldName();
                    String fileName = FilenameUtils.getName(item.getName());
                    InputStream fileContent = item.getInputStream();

//                    out.write(fieldName);
//                    out.write(fileName);


                    byte[] buffer = new byte[fileContent.available()];
                    fileContent.read(buffer);
                    String filepath = getServletContext().getRealPath("/") + fileName;
                    File targetFile = new File(filepath);
                    OutputStream outStream = new FileOutputStream(targetFile);
                    outStream.write(buffer);

                    out.println();

                    QualityController controller=new QualityController();
                    JsonArray print=controller.getQuality(targetFile.getAbsolutePath());
                    out.println(print.toString());





                    targetFile.delete();

                }
            }
        } catch (FileUploadException e) {
//            throw new ServletException("Cannot parse multipart request.", e);
            out.write(String.valueOf(e));
        }






    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException, IOException {
        PrintWriter out = response.getWriter();

        out.write("sachin");


    }
}

