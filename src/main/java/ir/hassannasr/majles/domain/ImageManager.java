package ir.hassannasr.majles.domain;

import ir.hassannasr.majles.services.candid.ImageServlet;

import java.io.*;
import java.util.Properties;

/**
 * Created by hassan on 03/01/2016.
 */
public class ImageManager {
    private static final String imageFolder;

    static {
        Properties properties = new Properties();
        try {
            properties.load(ImageServlet.class.getResourceAsStream("ImageServlet.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageFolder = properties.getProperty("imageLocation");
    }

    public static void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

        try {
            OutputStream out;
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(imageFolder + uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
