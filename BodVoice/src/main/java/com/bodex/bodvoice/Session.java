package com.bodex.bodvoice;

public class Session {
    private static String loggedInUsername;

    public static String getLoggedInUsername(){
        return loggedInUsername;
    }

    public static void setLoggedInUsername(String username){
        loggedInUsername = username;
    }

    public static void clearSession(){
        loggedInUsername = null;
    }
}
