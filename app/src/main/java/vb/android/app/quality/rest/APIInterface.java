package vb.android.app.quality.rest;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import rx.Observable;

/**
 * Describe the REST API with which the app is communicating
 */
public interface APIInterface {

    /**
     * Return the rank of this Pi computation (in term of performance).
     * @param algo is the algo used for the computation.
     * @param time is the time spend during computation.
     * @param max is the max arg to used to limit computation.
     * @return the rank.
     */
    @Headers("Content-Type: form-urlencoded; charset=utf-8")
    @GET("/algo/{algo}/time/{time}/max/{max}")
    Observable<ResponseRank> getRank(@Path("algo") String algo,
                                     @Path("time") double time,
                                     @Path("max") double max);

}
