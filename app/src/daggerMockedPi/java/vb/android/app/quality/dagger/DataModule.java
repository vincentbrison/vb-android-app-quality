package vb.android.app.quality.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.http.Path;
import rx.Observable;
import vb.android.app.quality.rest.APIInterface;
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
    public APIInterface provideApi() {
        return new MockAPIInterface();
    }

    private static class MockAPIInterface implements APIInterface {
        @Override
        public Observable<ResponseRank> getRank(
                @Path("algo") String algo, @Path("time") double time, @Path("max") double max) {
            ResponseRank rank = new ResponseRank();
            rank.setRank(2);
            return Observable.just(rank);
        }
    }
}
