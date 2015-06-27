package vb.android.app.quality.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vb.android.app.quality.app.QualityApplication;

/**
 * Module which expose Android application related objects.
 */
@Module
public class AppModule {

    private final Context mAppContext;

    /**
     * This module will hold the application context.
     * @param appContext is the context of the application.
     */
    public AppModule(QualityApplication appContext) {
        mAppContext = appContext;
    }

    @Provides
    @Singleton
    public Context getApplicationContext() {
        return mAppContext;
    }
}
