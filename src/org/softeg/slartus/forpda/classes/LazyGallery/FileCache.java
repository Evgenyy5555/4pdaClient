package org.softeg.slartus.forpda.classes.LazyGallery;

/**
 * User: slinkin
 * Date: 25.11.11
 * Time: 13:11
 */
import android.content.Context;
import org.softeg.slartus.forpda.MyApp;

import java.io.File;
import java.io.IOException;

public class FileCache {

    private File cacheDir;

    public FileCache(Context context) throws IOException {
        //Find the dir to save cached images
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir=new File(MyApp.INSTANCE.getAppExternalFolderPath()+"FileCache");
        else
            cacheDir=context.getCacheDir();
        if(!cacheDir.exists())
            cacheDir.mkdirs();
    }

    public File getFile(String url){
        //I identify images by hashcode. Not a perfect solution, good for the demo.
        String filename=String.valueOf(url.hashCode());
        //Another possible solution (thanks to grantland)
        //String filename = URLEncoder.encode(url);
        File f = new File(cacheDir, filename);
        return f;

    }

    public void clear(){
        File[] files=cacheDir.listFiles();
        for(File f:files)
            f.delete();
    }

}