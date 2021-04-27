package com.example.customfonts;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class ContextHelper {
    public static Typeface downloadedFontType;

    public static void downloadFont(Context context, String url) {
//        deleteExistingFonts(context);
        File fontFile = new File(context.getExternalFilesDir("File"), "fonts/downloadedFont.ttf");
        Log.d("INSIDE", "Inside downloading...");
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("Sample Font File");
        request.setTitle("downloadedFont.ttf");
        request.setVisibleInDownloadsUi(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        }

//        request.setDestinationUri(Uri.fromFile(new File(context.getExternalFilesDir("/File").toString() + "/fonts/downloadedFont.ttf")));
        request.setDestinationInExternalFilesDir(context, "/File", "/fonts/downloadedFont.ttf");

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Long downloadedID = Objects.requireNonNull(downloadManager).enqueue(request);

        if (DownloadManager.STATUS_SUCCESSFUL == 8) {
            Log.d("DONE", "Downloaded the font.");
            Log.d("FILE", "File exists?" + fontFile.exists());
            Log.d("ID", "Downloaded ID: " + downloadedID);
            Log.d("URI", "File URI: " + downloadManager.getUriForDownloadedFile(downloadedID));
            File file = new File(String.valueOf(downloadManager.getUriForDownloadedFile(downloadedID)));
            if(file.exists()){
                Typeface typeFace = Typeface.createFromFile(file);
                setDownloadedFontType(typeFace);
                Log.d("DONE", "Downloaded and set.");
            }
        }
    }

    public static void deleteExistingFonts(Context context) {
        File dir = new File(context.getExternalFilesDir("/File"), "fonts");
        if (dir.isDirectory()){
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++)
            {
                new File(dir, children[i]).delete();
            }
        }
    }

    public static void setDownloadedFontType (Typeface type){
        downloadedFontType = type;
    }

    public static Typeface getDownloadedFontType (){
        return downloadedFontType;
    }

}

