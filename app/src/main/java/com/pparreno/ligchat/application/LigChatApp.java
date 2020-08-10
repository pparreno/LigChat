package com.pparreno.ligchat.application;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.pparreno.ligchat.BuildConfig;

import timber.log.Timber;

public class LigChatApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    /**
     * A tree which logs important information for crash reporting.
     */
    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, @NonNull String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FirebaseCrashlytics firebaseCrashlytics = FirebaseCrashlytics.getInstance();
            firebaseCrashlytics.setCustomKey("priority", priority);
            firebaseCrashlytics.setCustomKey("tag", tag);

            if (t != null) {
                if (priority == Log.ERROR) {
                    firebaseCrashlytics.recordException(t);
                } else if (priority == Log.WARN) {
                    firebaseCrashlytics.log("Warning: " + message + " " + t.getMessage());
                }
            }
        }
    }

}
