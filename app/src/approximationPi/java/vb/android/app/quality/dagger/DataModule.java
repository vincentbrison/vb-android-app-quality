package vb.android.app.quality.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import vb.android.app.quality.R;
import vb.android.app.quality.rest.APIInterface;

/**
 * Module which expose data providers implementation used by the app.
 */
@Module(includes = {
        AppModule.class
})
public final class DataModule {

    /**
     * Return the REST API implementation used by this app.
     * @param context is used to resolve string.
     * @return the REST API implementation used by this app.
     */
    @Provides
    @Singleton
    public APIInterface provideApi(Context context) {
        return new RestAdapter.Builder()
                .setEndpoint(context.getString(R.string.url))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
                .create(APIInterface.class);
    }
}
