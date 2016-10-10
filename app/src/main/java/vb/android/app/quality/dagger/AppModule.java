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
import vb.android.app.quality.app.QualityApplication;

/**
 * Module which expose Android application related objects.
 */
@Module
public class AppModule {

    private final Context mAppContext;

    /**
     * This module will hold the application context.
     *
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
