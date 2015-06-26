package vb.android.app.quality.dagger;

import javax.inject.Singleton;

import dagger.Component;
import vb.android.app.quality.rest.APIInterface;
import vb.android.app.quality.ui.MainActivity;

/**
 * Component (dagger) with activity scope.
 */
@Singleton
@Component(modules = {DataModule.class})
public interface AppComponent {
    /**
     * Return the REST API used by the app.
     * @return the REST API used by the app.
     */
    APIInterface aPIInterface();

    /**
     * Inject the objects exposed by this component.
     * @param mainActivity is the activity to inject.
     */
    void inject(MainActivity mainActivity);
}

