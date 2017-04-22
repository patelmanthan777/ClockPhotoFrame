package com.tinyapps7.clockphotoframe;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.provider.MediaStore.Audio.Media;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PhotoBonkUtil {
    public static int calculateInSampleSize(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 <= i2 && i4 <= i) {
            return 1;
        }
        int round = Math.round(((float) i3) / ((float) i2));
        i3 = Math.round(((float) i4) / ((float) i));
        return round < i3 ? round : i3;
    }

    private static long fileUriFileSize(Context context, String str) {
        Cursor query = context.getContentResolver().query(Media.INTERNAL_CONTENT_URI, new String[]{"_display_name", "_size"}, "_display_name='" + Uri.parse(Uri.parse(str).getPath()).getLastPathSegment() + "'", null, null);
        if (query != null) {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("_size");
            if (query.moveToFirst()) {
                return query.getLong(columnIndexOrThrow);
            }
        }
        return 0;
    }

    private static String fileUriTitle(Context context, String str) {
        Cursor query = context.getContentResolver().query(Media.INTERNAL_CONTENT_URI, new String[]{"_display_name", "title"}, "_display_name='" + Uri.parse(Uri.parse(str).getPath()).getLastPathSegment() + "'", null, null);
        if (query == null) {
            return null;
        }
        return query.moveToFirst() ? query.getString(query.getColumnIndexOrThrow("title")) : null;
    }

    public static Bitmap imageFilePathToBitmap(String str, int i) {
        int i2 = 1;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeStream(new FileInputStream(str), null, options);
            int i3 = options.outWidth;
            int i4 = options.outHeight;
            while (i3 / 2 >= i && i4 / 2 >= i) {
                i3 /= 2;
                i4 /= 2;
                i2++;
            }
            options.inJustDecodeBounds = false;
            options.inSampleSize = i2;
            return BitmapFactory.decodeStream(new FileInputStream(str), null, options);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static Bitmap imageUriToBitmap(Context context, String str, int i) {
        return imageFilePathToBitmap(uriToFilePath(context, str), i);
    }

    public static long uriFileSize(Context context, String str) {
        Uri parse = Uri.parse(str);
        if (!parse.getScheme().equals("content")) {
            return fileUriFileSize(context, str);
        }
        Cursor query = context.getContentResolver().query(parse, new String[]{"_size"}, null, null, null);
        if (query != null) {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("_size");
            if (query.moveToFirst()) {
                return query.getLong(columnIndexOrThrow);
            }
        }
        return 0;
    }

    public static String uriTitle(Context context, String str) {
        String[] strArr = new String[]{"title"};
        Uri parse = Uri.parse(str);
        if (!parse.getScheme().equals("content")) {
            return fileUriTitle(context, str);
        }
        Cursor query = context.getContentResolver().query(parse, strArr, null, null, null);
        if (query == null) {
            return null;
        }
        return query.moveToFirst() ? query.getString(query.getColumnIndexOrThrow("title")) : null;
    }

    private static String uriToFilePath(Context context, String str) {
        if (Uri.parse(str).getScheme().equals("content")) {
            Cursor query = context.getContentResolver().query(Uri.parse(str), new String[]{"_data"}, null, null, null);
            if (query != null) {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
                if (query.moveToFirst()) {
                    return query.getString(columnIndexOrThrow);
                }
            }
        }
        return Uri.parse(str).getScheme().equals("file") ? Uri.parse(str).getPath() : null;
    }
}
