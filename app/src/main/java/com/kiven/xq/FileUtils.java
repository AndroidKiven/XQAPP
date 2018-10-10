package com.kiven.xq;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.os.Environment.MEDIA_MOUNTED;

/**
 * Created by hepengcheng on 2018/3/12.
 */

public class FileUtils {

    private static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";


    public static void writerString(File file, String str) {
        if (file == null || TextUtils.isEmpty(str)) {
            return;
        }
        FileOutputStream stream = null;
        OutputStreamWriter writer = null;
        BufferedWriter bufferedWriter = null;
        try {
            stream = new FileOutputStream(file);
            writer = new OutputStreamWriter(stream);
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String readString(File file) {
        if (file == null) {
            return "";
        }
        FileInputStream stream = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        try {
            stream = new FileInputStream(file);
            reader = new InputStreamReader(stream);
            bufferedReader = new BufferedReader(reader);
            StringBuilder stringBuilder = new StringBuilder();
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
