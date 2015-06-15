package vb.android.app.quality;

import vb.android.app.quality.app.QualityApplication;
import vb.android.app.quality.dagger.AppComponent;
import vb.android.app.quality.dagger.DaggerAppComponent;
import vb.android.app.quality.dagger.DataModule;

public enum Injector {
    INSTANCE;

    private AppComponent applicationComponent;

    private Injector(){
    }

    public void initializeApplicationComponent(QualityApplication customApplication) {
        //DataModule module = new DataModule();
        //AppComponent applicationComponent = DaggerAppComponent.builder().dataModule(module).build();
        this.applicationComponent = DaggerAppComponent.create();

    }

    public AppComponent getApplicationComponent() {
        return applicationComponent;
    }
}
