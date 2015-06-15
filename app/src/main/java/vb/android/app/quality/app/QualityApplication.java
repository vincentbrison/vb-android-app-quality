package vb.android.app.quality.app;

import android.app.Application;

import vb.android.app.quality.Injector;

/**
 * Created by Brize on 14/06/2015.
 */
public class QualityApplication extends Application {

    private static QualityApplication sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        Injector.INSTANCE.initializeApplicationComponent(this);
    }

    public static QualityApplication getApp() {
        return sApp;
    }

}
