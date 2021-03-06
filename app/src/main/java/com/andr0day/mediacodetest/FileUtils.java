package com.andr0day.mediacodetest;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;

import java.io.*;
import java.nio.charset.Charset;

public class FileUtils {

    public static String readFileToString(File file, Charset encoding) {
        InputStream in = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            in = openInputStream(file);
            copyFile(in, byteArrayOutputStream);
            return new String(byteArrayOutputStream.toByteArray(), encoding);
        } catch (Exception e) {
            //ignore
        } finally {
            try {
                in.close();
                byteArrayOutputStream.close();
            } catch (Exception e) {
                //ignore
            }
        }
        return null;
    }

    public static FileInputStream openInputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (file.canRead() == false) {
                throw new IOException("File '" + file + "' cannot be read");
            }
        } else {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }
        return new FileInputStream(file);
    }

    public static void copyAssetsToFiles(Context context, String fileName) {
        copyAssetsToFiles(context, fileName, fileName);
    }

    public static void copyAssetsToFiles(Context context, String srcFile, String dstFile) {
        AssetManager assetManager = context.getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(srcFile);
            out = context.openFileOutput(dstFile, Context.MODE_WORLD_WRITEABLE);
            copyFile(in, out);
        } catch (Exception e) {
            //ignore
        } finally {
            try {
                in.close();
                out.close();
            } catch (Exception e) {
                //ignore
            }
        }

    }

    public static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

}
