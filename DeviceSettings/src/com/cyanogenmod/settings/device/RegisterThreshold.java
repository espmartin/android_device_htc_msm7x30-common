package com.cyanogenmod.settings.device;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import java.lang.Integer;

public class RegisterThreshold {

    private static final String FILE = "/sys/android_touch/s2w_register_threshold";

    public static boolean isSupported() {
        return Utils.fileExists(FILE);
    }

    public static void restore(Context context) {
        if (!isSupported())
            return;
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        int value = sharedPrefs.getInt(DevicePreferenceActivity.KEY_REGISTER_THRESHOLD, getValue());
        Utils.writeValue(FILE, "" + value);
    }

    public static int getValue() {
        if (!isSupported())
            return 0;
        return Integer.parseInt(Utils.readOneLine(FILE));
    }

    public static void setValue(Context context, int value) {
        if (!isSupported())
            return;
        Utils.writeValue(FILE, "" + value);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(DevicePreferenceActivity.KEY_REGISTER_THRESHOLD, getValue());
        editor.commit();
    }

}
