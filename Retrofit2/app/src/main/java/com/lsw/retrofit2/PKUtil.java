package com.lsw.retrofit2;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;
import java.security.MessageDigest;

/**
 * Created by winbo on 2017/7/12.
 */

public class PKUtil {
    private static String m1;
    private static String m2;
    private static String l;
    private static String m;

    @SuppressLint({"DefaultLocale"})
    public static String getM1(Context ctx) {
        try {
            if ((m1 == null) && (ctx != null)) {
                m1 = MD5(getIMEI(ctx)).toLowerCase();
            }
        } catch (Exception ex) {
            Log.i("winbo", ex.getMessage());
        }
        return m1;
    }

    public static String getM2(Context ctx) {
        try {
            if (m2 == null) {
                m2 = MD5(getIMEI(ctx) + getSerialNumber() + getAndroidId(ctx.getContentResolver())).toLowerCase();
            }
        } catch (Exception ex) {
            m2 = "";
            Log.i("winbo", ex.getMessage());
        } catch (Error err) {
            m2 = "";
            Log.i("winbo", err.getMessage());
        }
        return m2;
    }

    protected static String getIMEI(Context paramContext) {
        String str = "";
        try {
            TelephonyManager localTelephonyManager = (TelephonyManager) paramContext.getSystemService("phone");
            str = localTelephonyManager.getDeviceId();
            if (str == null) {
                str = "";
            }
        } catch (Exception localException) {
        }
        return str;
    }

    protected static String MD5(String paramString) {
        char[] arrayOfChar1 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] arrayOfByte1 = paramString.getBytes();
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(arrayOfByte1);
            byte[] arrayOfByte2 = localMessageDigest.digest();
            int i1 = arrayOfByte2.length;
            char[] arrayOfChar2 = new char[i1 * 2];
            int i2 = 0;
            for (int i3 = 0; i3 < i1; i3++) {
                int i4 = arrayOfByte2[i3];
                arrayOfChar2[(i2++)] = arrayOfChar1[(i4 >>> 4 & 0xF)];
                arrayOfChar2[(i2++)] = arrayOfChar1[(i4 & 0xF)];
            }
            return new String(arrayOfChar2);
        } catch (Exception localException) {
        }
        return null;
    }

    private static String getSerialNumber() {
        if (l == null) {
            try {
                Class localClass = Class.forName("android.os.SystemProperties");
                Method localMethod = localClass.getMethod("get", new Class[]{String.class});
                l = (String) localMethod.invoke(localClass, new Object[]{"ro.serialno"});
            } catch (Exception localException) {
                l = "";
            }
        }
        return l;
    }

    private static String getAndroidId(ContentResolver paramContentResolver) {
        if (m == null) {
            m = Settings.System.getString(paramContentResolver, "android_id");
        }
        return m;
    }
}
