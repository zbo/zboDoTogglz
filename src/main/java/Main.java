import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by root on 8/19/15.
 */
public class Main {
    private static final int PORT=8090;
    public static void main(String[] args) throws Exception {
        Server server = new Server(PORT);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // http://localhost:8080/hello
        context.addServlet(new ServletHolder(new HelloServlet()), "/hello");
        // http://localhost:8080/hello/kongxx
        context.addServlet(new ServletHolder(new HelloServlet("Hello bob!")), "/hello/bob");

        // http://localhost:8080/goodbye
        context.addServlet(new ServletHolder(new GoodbyeServlet()), "/goodbye");
        // http://localhost:8080/goodbye/kongxx
        context.addServlet(new ServletHolder(new GoodbyeServlet("Goodbye bob!")), "/goodbye/bob");

        server.start();
        server.join();
    }
}
