package vb.android.app.quality;

import vb.android.app.quality.app.QualityApplication;
import vb.android.app.quality.dagger.AppComponent;
import vb.android.app.quality.dagger.AppModule;
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
     * @param application is the conttext of the application.
     */
    public static void initializeApplicationComponent(QualityApplication application) {
        sApplicationComponent = DaggerAppComponent.builder().appModule(new AppModule(application)).build();

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
