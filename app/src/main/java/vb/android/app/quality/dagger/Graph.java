package vb.android.app.quality.dagger;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Vincent Brison on 14/06/2015.
 * Graph for Dagger.
 */
@Singleton
@Component(modules = {DataModule.class})
public interface Graph {

    void inject(Activity activity);
}
