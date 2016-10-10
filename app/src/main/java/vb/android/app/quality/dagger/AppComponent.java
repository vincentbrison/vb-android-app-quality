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

import dagger.Component;
import vb.android.app.quality.rest.ApiInterface;
import vb.android.app.quality.ui.MainActivity;

/**
 * Component (dagger) with activity scope.
 */
@Singleton
@Component(modules = {
        DataModule.class, AppModule.class
})
public interface AppComponent {
    /**
     * Return the REST API used by the app.
     *
     * @return the REST API used by the app.
     */
    ApiInterface apiInterface();

    /**
     * Return the application context.
     *
     * @return the application context.
     */
    Context context();

    /**
     * Inject the objects exposed by this component.
     *
     * @param mainActivity is the activity to inject.
     */
    void inject(MainActivity mainActivity);
}
