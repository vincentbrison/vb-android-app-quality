package vb.android.app.quality.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import vb.android.app.quality.R;
import vb.android.app.quality.app.QualityApplication;
import vb.android.app.quality.rest.APIInterface;

/**
 * Module which expose data providers implementation used by the app.
 */
@Module
public final class DataModule {

    /**
     * Return the REST API implementation used by this app.
     * @return the REST API implementation used by this app.
     */
    @Provides
    @Singleton
    public APIInterface provideApi() {
            return new RestAdapter.Builder()
                    .setEndpoint(QualityApplication.getApp().getString(R.string.url))
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build()
                    .create(APIInterface.class);
    }
}
