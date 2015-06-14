package vb.android.app.quality.app;

import android.app.Activity;
import android.app.Application;

import vb.android.app.quality.dagger.DaggerGraph;
import vb.android.app.quality.dagger.Graph;

/**
 * Created by Brize on 14/06/2015.
 */
public class QualityApplication extends Application {

    private static QualityApplication sApp;
    private static Graph sGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        sGraph = DaggerGraph.create();
    }

    public static QualityApplication getApp() {
        return sApp;
    }

    public static Graph getGraph() {
        return sGraph;
    }
}
