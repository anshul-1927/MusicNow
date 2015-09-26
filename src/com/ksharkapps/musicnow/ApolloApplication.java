/*
 * Copyright (C) 2014 Kartik Sharma Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.ksharkapps.musicnow;

import android.app.Application;
import android.os.StrictMode;

import com.crittercism.app.Crittercism;
import com.ksharkapps.musicnow.cache.ImageCache;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Used to turn off logging for jaudiotagger and free up memory when
 * {@code #onLowMemory()} is called on pre-ICS devices. On post-ICS memory is
 * released within {@link ImageCache}.
 * 
 * @author Kartik Sharma (kshark.apps@gmail.com)
 */
public class ApolloApplication extends Application {
    private static final boolean DEBUG = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate() {
        // Enable strict mode logging
        enableStrictMode();
        // Turn off logging for jaudiotagger.
        Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);
        
        Crittercism.init(getApplicationContext(), "53e7557d83fb7914e6000002");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLowMemory() {
        ImageCache.getInstance(this).evictAll();
        super.onLowMemory();
    }

    private void enableStrictMode() {
        if (DEBUG) {
            final StrictMode.ThreadPolicy.Builder threadPolicyBuilder = new StrictMode.ThreadPolicy.Builder()
                    .detectAll().penaltyLog();
            final StrictMode.VmPolicy.Builder vmPolicyBuilder = new StrictMode.VmPolicy.Builder()
                    .detectAll().penaltyLog();

            threadPolicyBuilder.penaltyFlashScreen();
            StrictMode.setThreadPolicy(threadPolicyBuilder.build());
            StrictMode.setVmPolicy(vmPolicyBuilder.build());
        }
    }
}