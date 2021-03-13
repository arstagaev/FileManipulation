package com.arstagaev.creatingfiles1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private File telegramPath = null;
    private ArrayList<File> storageDirs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first();
        createFile();
        secondAttempt();
        create("Alloha");
    }

    private void secondAttempt() {
    }

    private File create(String name) {
        File baseDir;

        if (Build.VERSION.SDK_INT < 8) {
            baseDir = Environment.getExternalStorageDirectory();
        } else {
            baseDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        }

        if (baseDir == null)
            return Environment.getExternalStorageDirectory();

        File folder = new File(baseDir, name);

        if (folder.exists()) {
            return folder;
        }
        if (folder.isFile()) {
            folder.delete();
        }
        if (folder.mkdirs()) {
            return folder;
        }

        Log.d("ccc",baseDir+"");

        return Environment.getExternalStorageDirectory();
    }

    private void first() {
        if (Build.VERSION.SDK_INT >= 19) {
            storageDirs = AndroidUtilities.getRootDirs();
//            if (storageDirs.size() > 1) {
//                storageNumRow = rowCount++;
//            }
        }
        String storageDir = storageDirs.get(0).getAbsolutePath();
        SharedConfig.storageCacheDir = storageDir;
    }

    private void createFile() {

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File path = Environment.getExternalStorageDirectory();
            if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(SharedConfig.storageCacheDir)) {
                ArrayList<File> dirs = AndroidUtilities.getRootDirs();
                for (int a = 0, N = dirs.size(); a < N; a++) {
                    File dir = dirs.get(a);
                    if (dir.getAbsolutePath().startsWith(SharedConfig.storageCacheDir)) {
                        path = dir;
                        break;
                    }
                }
            } // /storage/self/primary
            telegramPath = new File(path, "Telegram");
            telegramPath.mkdirs();
            if (Build.VERSION.SDK_INT >= 19 && !telegramPath.isDirectory()) {
                ArrayList<File> dirs = AndroidUtilities.getDataDirs();
                if (dirs != null) {
                    for (int a = 0, N = dirs.size(); a < N; a++) {
                        File dir = dirs.get(a);
                        if (dir.getAbsolutePath().startsWith(SharedConfig.storageCacheDir)) {
                            path = dir;
                            telegramPath = new File(path, "Telegram");
                            telegramPath.mkdirs();
                            break;
                        }
                    }
                }
            }
        }
    }
}