# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# Remove all debug logs
# ref: https://www.guardsquare.com/en/products/proguard/manual/examples#logging
#-assumenosideeffects class android.util.Log {
#    public static *** d(...);
#}
# 去掉log日志：
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}
# 去掉System.out.println 和System.out.print输出
-assumenosideeffects class java.io.PrintStream {
    public *** println(...);
    public *** print(...);
}
