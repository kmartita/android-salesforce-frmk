package io.omni.example.helpers;

public class SessionManager {

    private static SessionHelper sessionHelper = null;

    public static SessionHelper getSession(){
        if(null == sessionHelper){
            sessionHelper = new SessionHelper();
        }
        return new SessionHelper();
    }
}
