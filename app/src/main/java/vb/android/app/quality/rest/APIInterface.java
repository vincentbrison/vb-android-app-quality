package vb.android.app.quality.rest;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import rx.Observable;

/**
 * Describe the REST API with which the app is communicating
 */
public interface APIInterface {
    @Headers("Content-Type: form-urlencoded; charset=utf-8")
    @GET("/algo/{algo}/time/{time}/max/{max}")
    Observable<ResponseRank> getRank(@Path("algo") String algo,
                                     @Path("time") double time,
                                     @Path("max") double max);

}
