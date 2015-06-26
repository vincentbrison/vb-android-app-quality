package vb.android.app.quality;

/**
 * List all the flavors.
 */
public enum Flavors {

    /**
     * PI is computed using a approximation.
     */
    APPROXIMATION_PI,

    /**
     * PI is computed using a mocked algorithm. REST communication is mocked through Dagger.
     */
    DAGGER_MOCKED_PI,

    /**
     * PI is computed using an exact algorithm.
     */
    EXACT_PI,

    /**
     * PI is computed using a mocked algorithm. REST communication is mocked through MockWebServer.
     */
    MOCK_WEB_SERVER_PI
}
