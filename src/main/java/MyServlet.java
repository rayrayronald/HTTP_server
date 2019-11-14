import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.stream.Collectors;
import java.util.logging.Logger;

@WebServlet(urlPatterns={"/patients"},loadOnStartup = 1)
public class MyServlet extends HttpServlet {
    private static final Logger log= Logger.getLogger(MyServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().write("Hello, world!");
        System.out.println(req.getServletPath());

        LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));

        log.info("In constructor");
        log.warning("Running low on memory");
        log.severe("Can't allocate memory");
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        resp.setContentType("text/html");
        resp.getWriter().write("Thank you client!");
        System.out.println(reqBody);
        Gson gson = new Gson();
        Patient p=gson.fromJson(reqBody,Patient.class);

        String fileName = "data_export.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.append(reqBody);
        writer.append('\n');

        writer.close();
        log.info(reqBody);
    }

}


