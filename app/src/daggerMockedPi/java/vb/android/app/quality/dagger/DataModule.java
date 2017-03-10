package vb.android.app.quality.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import retrofit2.http.Path;
import vb.android.app.quality.rest.ApiInterface;
import vb.android.app.quality.rest.ResponseRank;

/**
 * Module which expose data providers implementation used by the app.
 */
@Module
public final class DataModule {
    /**
     * Return the REST API implementation used by this app.
     *
     * @return the REST API implementation used by this app.
     */
    @Provides
    @Singleton
    public ApiInterface provideApi() {
        return new MockApiInterface();
    }

    private static class MockApiInterface implements ApiInterface {
        @Override
        public Observable<ResponseRank> getRank(
                @Path("algo") String algo, @Path("time") double time, @Path("max") double max) {
            ResponseRank rank = new ResponseRank();
            rank.setRank(2);
            return Observable.just(rank);
        }
    }
}
