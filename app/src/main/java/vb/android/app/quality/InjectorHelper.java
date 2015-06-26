package vb.android.app.quality;

import vb.android.app.quality.dagger.AppComponent;
import vb.android.app.quality.dagger.DaggerAppComponent;

/**
 * Helper to do dagger injection.
 */
public final class InjectorHelper {

    private static AppComponent sApplicationComponent;

    private InjectorHelper() {
    }

    /**
     * Init the dagger graph.
     */
    public static void initializeApplicationComponent() {
        sApplicationComponent = DaggerAppComponent.create();

    }

    /**
     * Return the app component to do injection.
     *
     * @return the app component to do injection.
     */
    public static AppComponent getApplicationComponent() {
        return sApplicationComponent;
    }
}
