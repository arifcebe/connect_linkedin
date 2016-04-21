package com.arifcebe.connectlinkedin.api;

/**
 * Created by arifcebe on 21/04/16.
 */
public class SharedPreferencesHelper {
    private static SharedPreferencesHelper ourInstance = new SharedPreferencesHelper();

    public static SharedPreferencesHelper getInstance() {
        return ourInstance;
    }

    private SharedPreferencesHelper() {
    }
}
