package com.itzyf.color;

/**
 * @author 依风听雨
 * @version 创建时间：2017/5/25 11:49
 */
public class SelectorBean {
    //默认颜色
    private String defaultSolidColor;
    private String defaultStrokeColor;

    //按下颜色
    private String pressedSolidColor;
    private String pressedStrokeColor;

    //不可点击颜色
    private String disabledSolidColor;
    private String disabledStrokeColor;

    public String getDefaultSolidColor() {
        return defaultSolidColor;
    }

    public void setDefaultSolidColor(String defaultSolidColor) {
        this.defaultSolidColor = defaultSolidColor;
    }

    public String getDefaultStrokeColor() {
        return defaultStrokeColor;
    }

    public void setDefaultStrokeColor(String defaultStrokeColor) {
        this.defaultStrokeColor = defaultStrokeColor;
    }

    public String getPressedSolidColor() {
        return pressedSolidColor;
    }

    public void setPressedSolidColor(String pressedSolidColor) {
        this.pressedSolidColor = pressedSolidColor;
    }

    public String getPressedStrokeColor() {
        return pressedStrokeColor;
    }

    public void setPressedStrokeColor(String pressedStrokeColor) {
        this.pressedStrokeColor = pressedStrokeColor;
    }

    public String getDisabledSolidColor() {
        return disabledSolidColor;
    }

    public void setDisabledSolidColor(String disabledSolidColor) {
        this.disabledSolidColor = disabledSolidColor;
    }

    public String getDisabledStrokeColor() {
        return disabledStrokeColor;
    }

    public void setDisabledStrokeColor(String disabledStrokeColor) {
        this.disabledStrokeColor = disabledStrokeColor;
    }
}
