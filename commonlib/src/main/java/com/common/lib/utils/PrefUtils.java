package com.common.lib.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;


public final class PrefUtils {

    private PrefUtils() {
    }

    /**
     * Preference Config
     **/
    public static final String CONFIG_NAME = "config";
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    private static Set<SharedPreferences.OnSharedPreferenceChangeListener> listeners = new HashSet<>();

    public static void init(Application app) {
        sp = app.getApplicationContext().getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }


    /**
     * save String value for key
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean saveStringForKey(String key, String value) {
        editor.putString(key, value).commit();
        notifyChanged(key);
        return true;
    }

    public static boolean saveStringForKeyUnNotifyChanged(String key, String value) {
        editor.putString(key, value).commit();
        return true;
    }

    public static void removeKey(String key) {
        editor.remove(key).commit();
        notifyChanged(key);
    }

    public static void removeKeyUnNotifyChanged(String key) {
        editor.remove(key).commit();
    }

    /**
     * 保存String 和 Int类型值
     *
     * @param key
     * @param obj
     * @return
     */
    public static boolean saveForKey(String key, Object obj) {
        if (null != obj) {
            if (obj instanceof String) {
                editor.putString(key, obj.toString());
            } else if (obj instanceof Integer) {
                editor.putInt(key, Integer.valueOf(obj.toString()));
            } else if (obj instanceof Boolean) {
                editor.putBoolean(key, Boolean.getBoolean(obj.toString()));
            } else {
                return false;
            }
        } else {
            return false;
        }
        editor.commit();
        notifyChanged(key);
        return true;
    }

    public static boolean saveForKeyUnNotifyChanged(String key, Object obj) {
        if (null != obj) {
            if (obj instanceof String) {
                editor.putString(key, obj.toString());
            } else if (obj instanceof Integer) {
                editor.putInt(key, Integer.valueOf(obj.toString()));
            } else if (obj instanceof Boolean) {
                editor.putBoolean(key, Boolean.getBoolean(obj.toString()));
            } else {
                return false;
            }
        } else {
            return false;
        }
        editor.commit();
        return true;
    }

    /**
     * load string value
     *
     * @param key
     * @return
     */
    public static String loadStringForKey(String key) {
        return sp.getString(key, "");
    }

    /**
     * load string value
     *
     * @param key
     * @return
     */
    public static String getStringForKey(String key) {
        return sp.getString(key, "");
    }

    public static String getStringForKey(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    /**
     * save int value for key
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean saveIntForKey(String key, int value) {
        editor.putInt(key, value).commit();
        notifyChanged(key);
        return true;
    }

    public static boolean saveIntForKeyUnNotifyChanged(String key, int value) {
        editor.putInt(key, value).commit();
        return true;
    }

    /**
     * load int value for key
     *
     * @return
     */
    public static int loadIntForKey(String key) {
        return sp.getInt(key, 0);
    }

    public static int getIntForKey(String key) {
        return sp.getInt(key, 0);
    }

    public static boolean hasKey(String key) {
        return sp.contains(key);
    }

    public static int getIntForKey(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public static float getFloatForKey(String key) {
        return sp.getFloat(key, 0.0f);
    }

    public static float getFloatForKey(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public static void saveFloatForKey(String key, float value) {
        editor.putFloat(key, value).commit();
        notifyChanged(key);
    }

    public static void saveFloatForKeyUnNotifyChanged(String key, float value) {
        editor.putFloat(key, value).commit();
    }

    /**
     * save Set for key
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean saveSetForKey(String key, Set<String> value) {
        editor.putStringSet(key, value).commit();
        notifyChanged(key);
        return true;
    }

    public static boolean saveSetForKeyUnNotifyChanged(String key, Set<String> value) {
        editor.putStringSet(key, value);
        return editor.commit();
    }


    /**
     * load Set value
     *
     * @param key
     * @return
     */
    public static Set<String> getSetForKey(String key) {
        return sp.getStringSet(key, new HashSet<String>());
    }

    public static Set<String> getSetForKey(String key, Set<String> defSet) {
        return sp.getStringSet(key, defSet);
    }

    public static boolean getBooleanForKey(String key) {
        return sp.getBoolean(key, false);
    }

    public static boolean getBooleanForKey(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public static boolean saveBooleanForKey(String key, boolean b) {
        editor.putBoolean(key, b).commit();
        notifyChanged(key);
        return true;
    }

    public static boolean saveBooleanForKeyUnNotifyChanged(String key, boolean b) {
        editor.putBoolean(key, b).commit();
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public static void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        listeners.remove(listener);
    }

    public static void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        listeners.add(listener);
    }

    private static void notifyChanged(final String key) {
        for (SharedPreferences.OnSharedPreferenceChangeListener listener : listeners) {
            if (listener != null) {
                listener.onSharedPreferenceChanged(sp, key);
            }
        }
    }

    public static void clear() {
        editor.clear().commit();
    }

}
