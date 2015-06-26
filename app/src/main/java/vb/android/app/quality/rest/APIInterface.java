package vb.android.app.quality.rest;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Describe the REST API with which the app is communicating
 */
public interface APIInterface {

    /**
     * Return the rank of the given performance.
     *
     * @param algo is the algorythm used to do computation.
     * @param time is the time spent during computation.
     * @param max  is the limit used to limit the computation.
     * @return the rank of the given performance.
     */
    @GET("/algo/{algo}/time/{time}/max/{max}")
    Observable<ResponseRank> getRank(@Path("algo") String algo,
                                     @Path("time") double time,
                                     @Path("max") double max);
}
