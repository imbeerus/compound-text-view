package com.lockwood.compoundemo.glide

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class AppGlideModule : AppGlideModule() {

    override fun applyOptions(
        context: Context,
        builder: GlideBuilder
    ) {
        super.applyOptions(context, builder)
        // Creates an DiskLruCache based disk cache in the external disk cache directory,
        // which falls back to the internal disk cache if no external storage is available
        builder.setDiskCache(ExternalPreferredCacheDiskCacheFactory(context))
        // enable hardware Bitmaps
        builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
    }

    override fun isManifestParsingEnabled() = false

}