package vb.android.app.quality.dagger;

import javax.inject.Singleton;

import dagger.Component;
import vb.android.app.quality.rest.APIInterface;
import vb.android.app.quality.ui.MainActivity;

/**
 * Component which provide application scoped injection.
 */
@Singleton
@Component(modules = {DataModule.class})
public interface AppComponent {

    /**
     * Let this object be exposed by this component.
     * @return the APIInterface class singleton which can be injected.
     */
    APIInterface aPIInterface();

    /**
     * Let an activity to be injected by this component.
     * @param mainActivity is the activity to inject.
     */
    void inject(MainActivity mainActivity);
}

