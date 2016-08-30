/*
 * Copyright 2015 Vincent Brison.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
                .baseUrl(context.getString(R.string.url) + ':' + context.getString(R.string.port))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build()
                .create(ApiInterface.class);
    }
}
