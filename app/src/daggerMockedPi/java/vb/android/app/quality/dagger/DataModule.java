package vb.android.app.quality.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.http.Path;
import rx.Observable;
import vb.android.app.quality.rest.APIInterface;
import vb.android.app.quality.rest.ResponseRank;

/**
 * Created by Brize on 14/06/2015.
 */
@Module
public final class DataModule {

    @Provides
    @Singleton
    public APIInterface provideApi() {
            return new MockAPIInterface();
    }

    private static class MockAPIInterface implements APIInterface {

        @Override
        public Observable<ResponseRank> getRank(@Path("algo") String algo, @Path("time") double time, @Path("max") double max) {
            ResponseRank rank = new ResponseRank();
            rank.setRank(2);
            return Observable.just(rank);
        }
    }
}
