import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.togglz.core.Feature;
import org.togglz.core.context.StaticFeatureManagerProvider;
import org.togglz.core.manager.DefaultFeatureManager;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;
import org.togglz.core.metadata.FeatureMetaData;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.user.FeatureUser;

import java.util.List;
import java.util.Set;

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

        //FeatureManager featureManager = context.getBean(FeatureManager.class);
        FeatureManager manager = new FeatureManagerBuilder()
                .togglzConfig(new FeatureFlagConfiguration())
                .build();
        StaticFeatureManagerProvider.setFeatureManager(manager);

        server.start();
        server.join();

    }
}
