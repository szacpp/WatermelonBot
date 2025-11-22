package com.example.watermelonbot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import java.nio.ByteBuffer;

public class ScreenReader {
    private MediaProjection projection;
    private ImageReader imageReader;
    private int width = 1080;
    private int height = 1920;
    private int density = 320;

    public ScreenReader(Context ctx) {
        MediaProjectionManager mgr = (MediaProjectionManager) ctx.getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        projection = mgr.getMediaProjection(ScreenCaptureActivity.screenResult, ScreenCaptureActivity.screenIntent);
        imageReader = ImageReader.newInstance(width, height, PixelFormat.RGBA_8888, 2);
        projection.createVirtualDisplay("screen-bot", width, height, density, 0, imageReader.getSurface(), null, null);
    }

    public Bitmap capture() {
        Image image = imageReader.acquireLatestImage();
        if (image == null) return null;
        Image.Plane[] planes = image.getPlanes();
        ByteBuffer buffer = planes[0].getBuffer();
        int pixelStride = planes[0].getPixelStride();
        int rowStride = planes[0].getRowStride();
        int rowPadding = rowStride - pixelStride * width;
        Bitmap bitmap = Bitmap.createBitmap(width + rowPadding / pixelStride, height, Bitmap.Config.ARGB_8888);
        bitmap.copyPixelsFromBuffer(buffer);
        image.close();
        return Bitmap.createBitmap(bitmap, 0, 0, width, height);
    }
}
