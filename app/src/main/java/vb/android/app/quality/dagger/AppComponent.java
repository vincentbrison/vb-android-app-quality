package vb.android.app.quality.dagger;

import javax.inject.Singleton;

import dagger.Component;
import vb.android.app.quality.rest.APIInterface;
import vb.android.app.quality.ui.MainActivity;

@Singleton
@Component(modules={DataModule.class})
public interface AppComponent {
    APIInterface aPIInterface();
    void inject(MainActivity mainActivity);
}

