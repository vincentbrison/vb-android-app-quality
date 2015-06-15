package vb.android.app.quality;

import vb.android.app.quality.dagger.AppComponent;
import vb.android.app.quality.dagger.DaggerAppComponent;

public enum Injector {
    /**
     * Hold an instance onto the component to do injections.
     */
    INSTANCE;

    private AppComponent applicationComponent;

    private Injector() {
    }

    /**
     * Init the component.
     */
    public void initializeApplicationComponent() {
        this.applicationComponent = DaggerAppComponent.create();

    }

    /**
     * Return the component.
     * @return the component.
     */
    public AppComponent getApplicationComponent() {
        return applicationComponent;
    }
}
