package ir.hassannasr.majles.services.candid;

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
        File file = new File(filePath);
        if (!file.exists()) {
            resp.sendError(404);
            return;
        }

        resp.setContentType("image/jpeg");
        BufferedImage bi = ImageIO.read(file);
        OutputStream out = resp.getOutputStream();
        ImageIO.write(bi, "jpg", out);
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