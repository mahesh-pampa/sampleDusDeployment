package com.sampleofdus;

import com.facebook.react.ReactActivity;

public class MainActivity extends ReactActivity {

    private final String bundleName = "example";
    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "sampleOfDus";
    }
    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new DusReactApplicationDelegate(this, getMainComponentName(), bundleName);
    }
}
