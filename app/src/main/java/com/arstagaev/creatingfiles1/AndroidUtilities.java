package com.arstagaev.creatingfiles1;

import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

public class AndroidUtilities {

    public static ArrayList<File> getDataDirs() {
        ArrayList<File> result = null;
        if (Build.VERSION.SDK_INT >= 19) {
            File[] dirs = ApplicationLoader.applicationContext.getExternalFilesDirs(null);
            if (dirs != null) {
                for (int a = 0; a < dirs.length; a++) {
                    if (dirs[a] == null) {
                        continue;
                    }
                    String path = dirs[a].getAbsolutePath();

                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(dirs[a]);
                }
            }
        }
        if (result == null) {
            result = new ArrayList<>();
        }
        if (result.isEmpty()) {
            result.add(Environment.getExternalStorageDirectory());
        }
        return result;
    }

    public static ArrayList<File> getRootDirs() {
        ArrayList<File> result = null;
        if (Build.VERSION.SDK_INT >= 19) {
            File[] dirs = ApplicationLoader.applicationContext.getExternalFilesDirs(null);
            if (dirs != null) {
                for (int a = 0; a < dirs.length; a++) {
                    if (dirs[a] == null) {
                        continue;
                    }
                    String path = dirs[a].getAbsolutePath();
                    int idx = path.indexOf("/Android");
                    if (idx >= 0) {
                        if (result == null) {
                            result = new ArrayList<>();
                        }
                        result.add(new File(path.substring(0, idx)));
                    }
                }
            }
        }
        if (result == null) {
            result = new ArrayList<>();
        }
        if (result.isEmpty()) {
            result.add(Environment.getExternalStorageDirectory());
        }
        return result;
    }
}
