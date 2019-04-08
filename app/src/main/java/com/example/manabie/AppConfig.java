package com.example.manabie;

import android.app.Application;
import android.os.StrictMode;

import com.example.manabie.utils.AppUtil;
import com.google.gson.Gson;

public class AppConfig extends Application {
    private static AppConfig sInstance;
    private static final String TAG = AppConfig.class.getSimpleName();
    private Gson mGson;

    private boolean isEmulator;

    //private static ApiClient mApiClient;

    // singleton with double-checker
    public static AppConfig getInstance() {
        AppConfig instance = sInstance;
        if (instance == null) {
            synchronized (AppConfig.class) {
                instance = sInstance;
                if (instance == null) {
                    instance = sInstance = new AppConfig();
                }
            }

        }

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            enableStrictMode();
        }
        isEmulator = AppUtil.isEmulator();
        sInstance = this;
        mGson = new Gson();
        //mApiClient = ApiClient.getApiClient();
    }

    /**
     * StrictMode is developer tool for detect things your might actions
     *  and correct your mistakes done during android app development
     */
    private void enableStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }

    /*public static ApiClient getApiClient() {
        return mApiClient;
    }*/

    public Gson getGson() {
        return mGson;
    }

}
