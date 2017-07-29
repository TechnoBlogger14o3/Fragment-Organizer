package com.fragment.flow.organizer.utility;



import com.fragment.flow.organizer.BuildConfig;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Techno Blogger on 25/5/16.
 */
public class AppLogger {

    public static void e(String tag, String msg) {
        if (!BuildConfig.ON_DEBUG_MODE)
            return;
        if (msg.length() > 4000) {
            android.util.Log.e(tag, msg.substring(0, 4000));
            e("", "" + checkInstanceOfException(msg.substring(4000)));
        } else
            android.util.Log.e(tag, "" + checkInstanceOfException(msg));
    }

    public static void i(String tag, String msg) {
        if (!BuildConfig.ON_DEBUG_MODE)
            return;
        if (msg.length() > 4000) {
            android.util.Log.i(tag, msg.substring(0, 4000));
            e("", "" + checkInstanceOfException(msg.substring(4000)));
        } else
            android.util.Log.i(tag, "" + checkInstanceOfException(msg));
    }

    public static void d(String tag, String msg) {
        if (!BuildConfig.ON_DEBUG_MODE)
            return;
        if (msg.length() > 4000) {
            android.util.Log.d(tag, msg.substring(0, 4000));
            e("", "" + checkInstanceOfException(msg.substring(4000)));
        } else
            android.util.Log.d(tag, "" + checkInstanceOfException(msg));
    }

    public static void w(String tag, String msg) {
        if (!BuildConfig.ON_DEBUG_MODE)
            return;
        if (msg.length() > 4000) {
            android.util.Log.w(tag, msg.substring(0, 4000));
            e("", "" + checkInstanceOfException(msg.substring(4000)));
        } else
            android.util.Log.w(tag, "" + checkInstanceOfException(msg));
    }

    private static Object checkInstanceOfException(Object msg) {
        try {
            if (msg instanceof Exception) {
                StringWriter errors = new StringWriter();
                ((Exception) msg).printStackTrace(new PrintWriter(errors));
                msg = errors.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
}
