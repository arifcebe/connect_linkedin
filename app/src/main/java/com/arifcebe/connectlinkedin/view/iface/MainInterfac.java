package com.arifcebe.connectlinkedin.view.iface;

import org.json.JSONObject;

/**
 * Created by arifcebe on 21/04/16.
 */
public interface MainInterfac {
    void fetchProfile(JSONObject jo);

    void processLogin();

    void logout();



}
