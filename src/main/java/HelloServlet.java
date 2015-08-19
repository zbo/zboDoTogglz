import javax.servlet.Servlet;

/**
 * Created by root on 8/19/15.
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String msg = "Hello World!";

    public HelloServlet() {
    }

    public HelloServlet(String msg) {
        this.msg = msg;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>" + msg + "</h1>");
        response.getWriter().println("session=" + request.getSession(true).getId());
        if( MyFeatures.FEATURE_ONE.isActive() ) {
            response.getWriter().println("Feature 1 <br/>");
        }
        if( MyFeatures.FEATURE_TWO.isActive() ) {
            response.getWriter().println("Feature 2 <br/>");
        }

    }
}

