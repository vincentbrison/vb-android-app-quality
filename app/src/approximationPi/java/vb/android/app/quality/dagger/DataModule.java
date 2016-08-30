package vb.android.app.quality.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import vb.android.app.quality.R;
import vb.android.app.quality.rest.ApiInterface;

/**
 * Module which expose data providers implementation used by the app.
 */
@Module(includes = {
        AppModule.class
})
public final class DataModule {
    /**
     * Return the OkHttpClient implementation used by this app.
     *
     * @return the OkHttpClient implementation used by this app.
     */
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    /**
     * Return the REST API implementation used by this app.
     *
     * @param context is used to resolve string.
     * @param client the OkHttpClient to use.
     * @return the REST API implementation used by this app.
     */
    @Provides
    @Singleton
    public ApiInterface provideApi(Context context, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(context.getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build()
                .create(ApiInterface.class);
    }
}
