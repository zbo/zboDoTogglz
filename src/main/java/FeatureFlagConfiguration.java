import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.mem.InMemoryStateRepository;
import org.togglz.core.user.UserProvider;
import org.togglz.servlet.user.ServletUserProvider;

public class FeatureFlagConfiguration implements TogglzConfig {
    private static final String featureAdminRole = "feature-admin";


    public Class<? extends Feature> getFeatureClass() {
        return MyFeatures.class;
    }

    public StateRepository getStateRepository() {
        return new InMemoryStateRepository();
    }

    public UserProvider getUserProvider() {
        return new ServletUserProvider(featureAdminRole);
    }
}