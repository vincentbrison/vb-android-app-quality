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

/**
 * List all the flavors.
 */
public enum Flavors {

    /**
     * PI is computed using a approximation.
     */
    APPROXIMATION_PI,

    /**
     * PI is computed using a mocked algorithm. REST communication is mocked through Dagger.
     */
    DAGGER_MOCKED_PI,

    /**
     * PI is computed using an exact algorithm.
     */
    EXACT_PI,

    /**
     * PI is computed using a mocked algorithm. REST communication is mocked through MockWebServer.
     */
    MOCK_WEB_SERVER_PI
}
