package com.example.loan.exampletab;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

/**
 * A cache that holds {@link Bitmap} instances.
 */
public class BitCache {

	/** Actual cache. */
	private LruCache<String, Bitmap> cache;

	private static volatile BitCache bitmapCache = null;


	public synchronized static BitCache get() {
		if (bitmapCache == null) {
			bitmapCache = new BitCache();
		}

		return bitmapCache;
	}


	private BitCache() {
		cache = new LruCache<String, Bitmap>(getMaxCacheSize()/*5000*/) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return (value.getByteCount() / 1024);
			}

			@Override
			protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
				super.entryRemoved(evicted, key, oldValue, newValue);
				Log.e("TAG", "Removing: " + key);
			}

			@Override
			protected Bitmap create(String key) {
				Log.e("TAG", "Adding:  " + key);
				return super.create(key);
			}
		};
	}


	/**
	 * Calculates/Gets the maximum size (in bytes) this cache is allowed to grow to.
	 *
	 * @return Maximum cache size in bytes.
	 */
	private int getMaxCacheSize() {
		// get the approximate memory (in bytes) this app is assigned to
		final int maxMem = (int) Runtime.getRuntime().maxMemory();

		// use 1/8th of the available memory for this memory cache
		int maxCacheMem = (maxMem / 1024) / 8;
		Log.e("TAG", "maxCacheMem:  " + maxCacheMem + "KiB");
		return maxCacheMem;
	}


	/**
	 * Adds a {@link Bitmap} to cache.
	 *
	 * @param bitmapID The bitmap ID (e.g. URL).
	 * @param bitmap Bitmap instance.
	 */
	public synchronized void add(String bitmapID, Bitmap bitmap) {
		if (bitmapID != null  &&  bitmap != null)
			cache.put(bitmapID, bitmap);
	}


	/**
	 * Searches for the bitmap stored in this cache whose ID is equal to bitmapID.
	 *
	 * @param bitmapId Bitmap ID
	 * @return Bitmap instance if found; null otherwise.
	 */
	public synchronized Bitmap get(String bitmapId) {
		return bitmapId != null ? cache.get(bitmapId) : null;
	}
//Đơn giản ko, đây đã đầ đủ nha e
	//dua vao sao ạ a
	//E nhìn a sử dụng nha
}
