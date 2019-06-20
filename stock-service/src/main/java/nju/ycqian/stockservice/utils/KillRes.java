package nju.ycqian.stockservice.utils;

import org.omg.CORBA.PUBLIC_MEMBER;

public class KillRes {
    public static final int KILL_SUCC = 0;
    public static final int KILL_NOT_FOUND = 1;
    public static final int KILL_NOT_ENOUGH = 2;
    public static final int KILL_NOT_BEGIN = 3;
    public static final int KILL_ENDED = 4;
    public static final int ERROR = 5;

    private static final String MSG_NOT_FOUND = "GOODS NOT FOUND";
    private static final String MSG_NOT_ENOUGH = "GOODS NOT ENOUGH";
    private static final String MSG_NOT_BEGIN = "KILL NOT BEGIN";
    private static final String MSG_ENDED = "KILL ENDED";
    private static final String MSG_ERROR = "INTERNAL ERROR";
    private static final String[] MSGS = {"", MSG_NOT_FOUND, MSG_NOT_ENOUGH, MSG_NOT_BEGIN, MSG_ENDED, MSG_ERROR};

    public static String getMsg(int i) {
        return MSGS[i];
    }
}
