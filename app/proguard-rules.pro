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

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#-keep class com.we.** {*;}
#-dontwarn android.support.v7.**
#-keep class android.support.v7.** { *; }
#-keep public class * extends android.support.v7.**
#-keep public class * extends android.app.Fragment
#-keep public class * extends android.support.v4.**
#-keep class android.support.v4.** { *; }
#-keep interface android.support.v4.app.** { *; }

#common
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-ignorewarnings

-keep class android.app.**{*;}

-keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
}

#Butter Knife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#greendao
-keep class org.greenrobot.greendao.**{*;}
-keep public interface org.greenrobot.greendao.**
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
     public static java.lang.String TABLENAME;
}
-keep class **$Properties
-keep class net.sqlcipher.database.**{*;}
-keep public interface net.sqlcipher.database.**
-dontwarn net.sqlcipher.database.**
-dontwarn org.greenrobot.greendao.**

#event bus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

-keep class com.planet.light2345.event.**{*;}

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

#fastjson
-dontwarn android.support.**
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }

-keep class com.magicbox2345.base.activity.**{*;}
-keep class com.magic2345.socketbridge.** { *; }

#AgentWeb
-keep class com.just.agentweb.** {
    *;
}
-dontwarn com.just.agentweb.**

#小米推送
-keep class com.xiaomi.mipush.sdk.MiPushMessageReceiver {*;}

#用户中心登录
-keep class com.usercenter2345.library1.model.**{*;}
-keep class com.usercenter2345.library1.util.**{*;}
-keep class com.usercenter2345.library1.network.callback.**{*;}
-keep class com.usercenter2345.library1.network.request.**{*;}
-keep class com.usercenter2345.library1.network.**{*;}
-keep class com.usercenter2345.library1.UserCenter2345Manager{*;}
-keep class com.usercenter2345.library1.UserCenterConfig{*;}
#用户中心核心库
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.**{*;}
-dontwarn okio.**
#联合登录
-keep class com.usercenter2345.unionlogin.**{*;}
#移动武林榜
-keep class com.statistic2345.**{*;}


-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-ignorewarnings
-dontoptimize
-dontpreverify

-keepattributes *Annotation*
-keep class * extends java.lang.annotation.Annotation {*;}
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService


# keep annotated by NotProguard
-keep @com.planet.light2345.annotation.NotProguard class * {*;}

#bean
-keep class com.planet.light2345.certification.bean.**{*;}

#Face++
-keep public class com.megvii.**{*;}

-keep public class android.net.http.SslError

-dontwarn android.webkit.WebView
-dontwarn android.net.http.SslError
-dontwarn Android.webkit.WebViewClient

# 拉活SDK（包含锁屏信息流）
-keep class com.we.** {*;}
-dontwarn android.support.v7.**
-keep class android.support.v7.** { *; }
-keep public class * extends android.support.v7.**
-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
