package com.baway.week_2_1205;


import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * 作者：方诗康
 *   使用image-loader全局适配
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .diskCache(new UnlimitedDiskCache(new File(getCacheDir().getAbsolutePath())))
                .diskCacheSize(1024*10)
                .memoryCache(new LruMemoryCache(1024*10))
                .diskCacheSize(1024*10)
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}

