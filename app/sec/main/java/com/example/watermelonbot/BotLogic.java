package com.example.watermelonbot;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Path;
import android.accessibilityservice.GestureDescription;

public class BotLogic {

    public static String detectFruit(Bitmap bmp) {
        int x = bmp.getWidth() / 2;
        int y = 200;
        int pixel = bmp.getPixel(x, y);
        int r = Color.red(pixel);
        int g = Color.green(pixel);
        int b = Color.blue(pixel);
        if (r > 180 && g < 80) return "cherry";
        if (r > 150 && g < 80 && b < 80) return "strawberry";
        if (r < 150 && b > 100) return "grape";
        if (r > 200 && g > 100) return "orange";
        if (g > 150 && r < 150) return "pear";
        if (g > 120 && b < 80) return "apple";
        return "unknown";
    }

    public static int findBestColumn(int[] heights) {
        int best = 0;
        int maxH = -1;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] > maxH) {
                maxH = heights[i];
                best = i;
            }
        }
        return best;
    }

    // Example move gesture (to be called from AccessibilityService)
    public static GestureDescription buildMoveGesture(int startX, int startY, int endX, int endY, long duration) {
        Path p = new Path();
        p.moveTo(startX, startY);
        p.lineTo(endX, endY);
        GestureDescription.StrokeDescription sd = new GestureDescription.StrokeDescription(p, 0, duration);
        GestureDescription.Builder b = new GestureDescription.Builder();
        b.addStroke(sd);
        return b.build();
    }
}
