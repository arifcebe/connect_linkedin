package com.arifcebe.connectlinkedin.api;

/**
 * Created by arifcebe on 20/04/16.
 */
public class Webservice {
    private static Webservice ourInstance = new Webservice();
    private static final String host ="api.linkedin.com";
    public static final String baseUrl = "https://" + host + "/v1/";
    private final String getUrlPeopleProfile = "people/~:(first-name,last-name,public-profile-url,picture-url)";
    private final String getUrlShare = "people/~/shares";

    public static Webservice getInstance() {
        return ourInstance;
    }

    private Webservice() {
    }

    public String getGetUrlPeopleProfile() {
        return baseUrl + getUrlPeopleProfile;
    }

    /**
     *
     * @return
     */
    public String getGetUrlShare() {
        return baseUrl + getUrlShare;
    }
}
