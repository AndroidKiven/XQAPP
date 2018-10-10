package com.kiven.xq;

import android.app.Application;
import android.util.Log;

import com.mobile2345.ads.MobileAds;
import com.we.interfaces.LockCloseListener;
import com.we.interfaces.SdkInitListener;
import com.we.setting.AdSetting;
import com.we.setting.LiveSetting;

/**
 * Created by hepengcheng on 2018/7/18.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.init(this.getApplicationContext(), true, new SdkInitListener() {
            @Override
            public void onFail() {
                Log.e("hpc_kiven","fail");
            }

            @Override
            public void onSuccess() {
                Log.e("hpc_kiven","onSuccess");
                /*AdSetting.getInstance(MyApplication.this).setAdConfig("100046", "10004601,10004602,10004603");*/

                LiveSetting.getInstance(MyApplication.this).setLockCloseListener(new LockCloseListener() {
                    @Override
                    public void switchStateClosed() {
                        Log.e("MobAdsSdk", "锁屏被关闭");
                        LiveSetting.getInstance(MyApplication.this).setLockSwitchState(0, "123456789");
                    }
                });
                AdSetting.getInstance(MyApplication.this).getSettingBuilder()
                        .setAppId("12")
                        .setUmengChannel("xq_zhuban")
                        .setBdAppId("b8e7f314")
                        .setBdAdId("5842235")
                        .setMainAdId("100046")
                        .setSubSenseIds("10004601,10004602,10004603")
                        .setLockFloatAdId("10004901")
                        .build();


            }
        });
    }
}


