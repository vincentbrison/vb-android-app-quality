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

package vb.android.app.quality.rest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Describe the REST API with which the app is communicating
 */
public interface ApiInterface {
    /**
     * Return the rank of the given performance.
     *
     * @param algo is the algorithm used to do computation.
     * @param time is the time spent during computation.
     * @param max  is the limit used to limit the computation.
     * @return the rank of the given performance.
     */
    @GET("/algo/{algo}/time/{time}/max/{max}")
    Observable<ResponseRank> getRank(@Path("algo") String algo,
                                     @Path("time") double time,
                                     @Path("max") double max);
}
