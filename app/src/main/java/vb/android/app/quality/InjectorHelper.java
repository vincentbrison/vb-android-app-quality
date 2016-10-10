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

package vb.android.app.quality;

import vb.android.app.quality.app.QualityApplication;
import vb.android.app.quality.dagger.AppComponent;
import vb.android.app.quality.dagger.AppModule;
import vb.android.app.quality.dagger.DaggerAppComponent;

/**
 * Helper to do dagger injection.
 */
public final class InjectorHelper {
    private static AppComponent sApplicationComponent;

    private InjectorHelper() {
    }

    /**
     * Init the dagger graph.
     *
     * @param application is the context of the application.
     */
    public static void initializeApplicationComponent(QualityApplication application) {
        sApplicationComponent = DaggerAppComponent.builder().appModule(new AppModule(application)).build();
    }

    /**
     * Return the app component to do injection.
     *
     * @return the app component to do injection.
     */
    public static AppComponent getApplicationComponent() {
        return sApplicationComponent;
    }
}
