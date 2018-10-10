package com.kiven.xq;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.we.setting.LiveSetting;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity {

    private TextView tv_public_data, tv_private_data;

    private EditText et_passId;

    private String mPassId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_public_data = findViewById(R.id.tv_public_data);
        tv_private_data = findViewById(R.id.tv_private_data);
        et_passId = findViewById(R.id.et_passId);

        SpUtils spUtils = new SpUtils("user_data", MainActivity.this.getApplicationContext());
        boolean result = spUtils.getBoolean("login", false);
        if (result) {
            Toast.makeText(this.getApplicationContext(), "已自动登录", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getApplicationContext(), "您还未登录", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 开启
     *
     * @param view
     */
    public void startLive(View view) {
        SpUtils spUtils = new SpUtils("user_data", MainActivity.this.getApplicationContext());
        boolean result = spUtils.getBoolean("login", false);
        if (result) {
            sendState("401089600", 1);
        } else {
            Toast.makeText(this.getApplicationContext(), "先登录", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 关闭
     *
     * @param view
     */
    public void stopLive(View view) {
        SpUtils spUtils = new SpUtils("user_data", MainActivity.this.getApplicationContext());
        boolean result = spUtils.getBoolean("login", false);
        if (result) {
            sendState("401089600", 2);
        } else {
            Toast.makeText(this.getApplicationContext(), "先登录", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 登录成功
     *
     * @param view
     */
    public void login(View view) {
        requestState("401089600");
    }

    /**
     * 退出登录
     *
     * @param view
     */
    public void logout(View view) {
        SpUtils spUtils = new SpUtils("user_data", MainActivity.this.getApplicationContext());
        spUtils.putBoolean("login", false);
        LiveSetting.getInstance(this.getApplicationContext()).logoutXQ();
        Toast.makeText(this.getApplicationContext(), "退出登录了", Toast.LENGTH_LONG).show();
    }


    public void requestState(String passId) {
        /*try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://guess.union2.50bang.org/home.php/PullAlive-statusChange?passid=" + passId)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String result = response.body().string();
                        JSONObject jsonObject = new JSONObject(result);
                        int btnStatus = jsonObject.optInt("btnStatus");
                        LiveSetting.getInstance(MainActivity.this).setPullSwitchState("401089600", "18888888888", btnStatus);
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                SpUtils spUtils = new SpUtils("user_data", MainActivity.this.getApplicationContext());
                                spUtils.putBoolean("login", true);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public void sendState(String passId, final int btnStatus) {
        /*try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://guess.union2.50bang.org/home.php/PullAlive-statusChange?passid=" + passId + "&btnStatus=" + btnStatus)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "上报状态改变失败", Toast.LENGTH_LONG).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        LiveSetting.getInstance(MainActivity.this).setPullSwitchState("401089600", "18888888888", btnStatus);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public void showPublic(View view) {
        tv_public_data.setText("");
        String publicDir = getPublicDirData();
        tv_public_data.setText(publicDir);
    }


    public void showPrivate(View view) {
        tv_private_data.setText("");
        SpUtils spUtils = new SpUtils("live_data", this.getApplicationContext());
        String passId = spUtils.getString(KEY_PASSID);
        String phoneNum = spUtils.getString(KEY_PHONE_NUM);
        int oldSwitchState = spUtils.getInt(KEY_SWITCH_STATE);
        long time = spUtils.getLong(KEY_CHANGE_TIME);
        int newsState = spUtils.getInt(KEY_NEWS_STATE);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(KEY_PASSID, passId);
            jsonObject.put(KEY_PHONE_NUM, phoneNum);
            jsonObject.put(KEY_SWITCH_STATE, oldSwitchState);
            jsonObject.put(KEY_CHANGE_TIME, time);
            jsonObject.put(KEY_NEWS_STATE, newsState);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv_private_data.setText(jsonObject.toString());
    }

    public void lockOpen(View view) {
        //调用开关为开
        LiveSetting.getInstance(this.getApplicationContext()).setLockSwitchState(1, mPassId);
    }

    public void lockClose(View view) {
        //调用开关为关
        LiveSetting.getInstance(this.getApplicationContext()).setLockSwitchState(0, mPassId);
    }

    public void serverReturnOpen(View view) {
        LiveSetting.getInstance(this.getApplicationContext()).setLockSwitchState(1, mPassId);
    }

    public void serverReturnClose(View view) {
        LiveSetting.getInstance(this.getApplicationContext()).setLockSwitchState(0, mPassId);
    }

    public void liveReturnOpen(View view) {
        LiveSetting.getInstance(this.getApplicationContext()).setPullSwitchState(mPassId, "188888888", 1);
    }

    public void liveReturnClose(View view) {
        LiveSetting.getInstance(this.getApplicationContext()).setPullSwitchState(mPassId, "188888888", 0);
    }

    public void liveOpen(View view) {
        LiveSetting.getInstance(this.getApplicationContext()).setPullSwitchState(mPassId, "188888888", 1);
    }

    public void liveClose(View view) {
        LiveSetting.getInstance(this.getApplicationContext()).setPullSwitchState(mPassId, "188888888", 0);
    }

    public void setPassId(View view) {
        String content = et_passId.getText().toString();
        if (!TextUtils.isEmpty(content)) {
            mPassId = content;
        }
        Log.e("hpc_kiven", "mPassId = " + mPassId);
    }

    public static final String XQ_PUBLIC_DIR = Environment.getExternalStorageDirectory() + "/.system/" + Md5Utils.Md5("xq_data");

    public static final String KEY_PHONE_NUM = "phone_num";

    public static final String KEY_PASSID = "passid";

    public static final String KEY_SWITCH_STATE = "switch_state";
    /**
     * 开关状态改变时间
     */
    public static final String KEY_CHANGE_TIME = "change_time";

    public static final String KEY_NEWS_STATE = "lock_switch_state";

    /**
     * 获取公有目录数据
     */
    private static String getPublicDirData() {
        File file = new File(XQ_PUBLIC_DIR);
        if (!file.exists()) {
            return "";
        }
        String str = FileUtils.readString(file);
        if (str != null) {
            return EncryptUtils.decrypt(str);
        }
        return "";
    }
}
