package vb.android.app.quality.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import vb.android.app.quality.R;
import vb.android.app.quality.app.QualityApplication;
import vb.android.app.quality.rest.APIInterface;

/**
 * Created by Brize on 14/06/2015.
 */
@Module
public final class DataModule {

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
