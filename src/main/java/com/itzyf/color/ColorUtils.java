package com.itzyf.color;


public class ColorUtils {
    private int color;
    public static final int DISABLED_ALPHA_FILL = 165;
    public static final int DISABLED_ALPHA_EDGE = 190;
    public static final float ACTIVE_OPACITY_FACTOR_FILL = 0.125f;
    public static final float ACTIVE_OPACITY_FACTOR_EDGE = 0.025f;

    public ColorUtils(int color) {
        this.color = color;
    }

    public static int decreaseRgbChannels(int c, float percent) {
        // reduce rgb channel values to produce box shadow effect
        int red = (Color.red(c));
        red -= (red * percent);
        red = red > 0 ? red : 0;

        int green = (Color.green(c));
        green -= (green * percent);
        green = green > 0 ? green : 0;

        int blue = (Color.blue(c));
        blue -= (blue * percent);
        blue = blue > 0 ? blue : 0;

        return Color.argb(Color.alpha(c), red, green, blue);
    }

    public static int increaseOpacity(int color, int alpha) {
        return increaseOpacityFromInt(color, alpha);
    }

    public static int increaseOpacityFromInt(int c, int alpha) {
        return Color.argb(alpha, Color.red(c), Color.green(c), Color.blue(c));
    }

    public int defaultEdge() {
        return decreaseRgbChannels(color, ACTIVE_OPACITY_FACTOR_EDGE);
    }

    public int activeFill() {
        return decreaseRgbChannels(color, ACTIVE_OPACITY_FACTOR_FILL);
    }

    public int activeEdge() {
        return decreaseRgbChannels(color, ACTIVE_OPACITY_FACTOR_FILL + ACTIVE_OPACITY_FACTOR_EDGE);
    }

    public int disabledFill() {
        return increaseOpacity(color, DISABLED_ALPHA_FILL);
    }

    public int disabledEdge() {
        return increaseOpacity(color, DISABLED_ALPHA_FILL - DISABLED_ALPHA_EDGE);
    }
}