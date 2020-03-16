package com.sampleofdus;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

  private final DusReactNativeHost mReactNativeHost = new DusReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return false;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage()
            );
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
        /**
         * Fetches the latest update graph on app launch
         * which can be used to fetch new bundles on demand
         */
        AsyncTask.SERIAL_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = getApplicationContext().getContentResolver().query(DUSContracts.buildFetchUpdateGraphUri(), null, null, null, null);
                if (cursor != null) {
                    cursor.close();
                }
            }
        });
    }

    /**
     * We inject all the dependencies into DUS: ComponentDownloader, FileConfigDownloader
     * PackagedDbName, DusLogger and packagedDbVersion
     * @return DusDependencyResolver - all dependencies required by DUS
     */
    @Override
    public DusDependencyResolver getDusDependencyResolver() {
        DusDependencyResolver dependencyResolver = new DusDependencyResolver();
        dependencyResolver.setComponentRequestInterface(new ComponentDownloader())
                .setDusLogger(new DusLoggerResolver())
                .setFileConfigRequestInterface(new FileConfigDownloader())
                .setPackagedDbName("")
                .setPackagedDbVersion(0);
        return dependencyResolver;
    }

    @Override
    public DusReactNativeHost getDusReactNativeHost() {
        return mReactNativeHost;
    }
}
