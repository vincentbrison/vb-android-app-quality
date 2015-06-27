package vb.android.app.quality.app;

import android.app.Application;

import vb.android.app.quality.InjectorHelper;

/**
 * Subclass of app to access easily to the application context and have a callback when the app is created.
 */
public class QualityApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        InjectorHelper.initializeApplicationComponent(this);
    }
}
