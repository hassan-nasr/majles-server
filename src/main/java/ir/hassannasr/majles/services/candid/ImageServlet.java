package ir.hassannasr.majles.services.candid;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

/**
 * Created by hassan on 15/12/2015.
 */

//@WebServlet(name = "image", urlPatterns = "/candidImage")
public class ImageServlet extends HttpServlet {


    private final int BUFSIZE = 4096;
    String imageLocation;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String filePath = imageLocation + req.getParameter("imageId");

        final int cacheAge = 60 * 60 * 24 * 7;
        long expiry = new Date().getTime() + cacheAge * 1000;

        resp.setDateHeader("Expires", expiry);
        resp.setHeader("Cache-Control", "max-age=" + cacheAge);
        File file = new File(filePath);
        if (!file.exists()) {
            resp.sendError(404);
            return;
        }

        newMethod(resp, file);

    }

    private void newMethod(HttpServletResponse resp, File file) throws IOException {
        //Find a suitable ImageReader

        resp.setContentType("image/jpeg");
        OutputStream out = resp.getOutputStream();
        FileUtils.copyFile(file, out);
        out.close();
    }

    private void oldMethod(HttpServletResponse resp, File file) throws IOException {
        resp.setContentType("image/png");
        BufferedImage bi = ImageIO.read(file);
        OutputStream out = resp.getOutputStream();
        ImageIO.write(bi, "png", out);
        out.close();
    }

    public void init(ServletConfig config) throws ServletException {
        try {
            final Properties properties = new Properties();
            properties.load(this.getClass().getResourceAsStream("ImageServlet.properties"));
            imageLocation = properties.getProperty("imageLocation");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}