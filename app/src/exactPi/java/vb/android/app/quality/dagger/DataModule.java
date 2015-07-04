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

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
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
                    .setEndpoint("http://echo.jsontest.com")
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build()
                    .create(APIInterface.class);
    }
}
