package top.zloop.mobile.lib.utils;

import java.util.Locale;

public class Device {

    String DeviceModel = android.os.Build.MODEL;
    String OS_Version = System.getProperty("os.version");
    String Build_Version = android.os.Build.VERSION.RELEASE;
    String Device = android.os.Build.DEVICE;
    String PRODUCT = android.os.Build.PRODUCT;
    String BRAND = android.os.Build.BRAND;
    String DISPLAY = android.os.Build.DISPLAY;
    String CPU_ABI = android.os.Build.CPU_ABI;
    String CPU_ABI2 = android.os.Build.CPU_ABI2;
    String UNKNOWN = android.os.Build.UNKNOWN;
    String HARDWARE = android.os.Build.HARDWARE;
    String ID = android.os.Build.ID;
    String MANUFACTURER = android.os.Build.MANUFACTURER;
    String SERIAL = android.os.Build.SERIAL;
    String USER = android.os.Build.USER;
    String HOST = android.os.Build.HOST;

    public static final String USER_AGENT = "Android "
            + android.os.Build.VERSION.RELEASE + ";"
            + Locale.getDefault().toString() + "; " + android.os.Build.DEVICE
            + "/" + android.os.Build.ID + ")";
}
