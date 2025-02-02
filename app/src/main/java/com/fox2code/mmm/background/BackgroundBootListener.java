package com.fox2code.mmm.background;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.fox2code.mmm.MainApplication;

public class BackgroundBootListener extends BroadcastReceiver {
    private static final String BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!BOOT_COMPLETED.equals(intent.getAction())) return;
        synchronized (BackgroundUpdateChecker.lock) {
            BackgroundUpdateChecker.onMainActivityCreate(context);
            if (MainApplication.isBackgroundUpdateCheckEnabled()) {
                BackgroundUpdateChecker.doCheck(context);
            }
        }
    }
}
