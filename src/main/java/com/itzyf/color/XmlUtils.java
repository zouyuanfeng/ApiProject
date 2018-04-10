package com.itzyf.color;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 依风听雨
 * @version 创建时间：2017/5/25 11:41
 */
class XmlUtils {

    private int radius = 5;
    private String fileName = "btn_selector";
    private int padding = 0;

    public static XmlUtils getInstance() {
        return new XmlUtils();
    }

    void createSelector(SelectorBean bean) {
        Element root = DocumentHelper.createElement("selector");
        Document doucment = DocumentHelper.createDocument(root);
        //根节点
        root.addAttribute("xmlns:android", "http://schemas.android.com/apk/res/android");

        createItem(root, bean.getDefaultSolidColor(), bean.getDefaultStrokeColor(), 0);
        createItem(root, bean.getPressedSolidColor(), bean.getPressedStrokeColor(), 1);
        createItem(root, bean.getDisabledSolidColor(), bean.getDisabledStrokeColor(), 2);

        writerFile(doucment);
    }

    public XmlUtils setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    public XmlUtils setFileName(String fileName) {
        if (!fileName.endsWith(".xml"))
            fileName += ".xml";
        this.fileName = fileName;
        return this;
    }

    public XmlUtils setPadding(int padding) {
        this.padding = padding;
        return this;
    }

    private void writerFile(Document doucment) {
        XMLWriter xml = null;
        try {

            //设置文件编码
            OutputFormat xmlFormat = new OutputFormat();
            xmlFormat.setEncoding("UTF-8");
            // 设置换行
            xmlFormat.setNewlines(true);
            // 生成缩进
            xmlFormat.setIndent(true);
            // 使用4个空格进行缩进, 可以兼容文本编辑器
            xmlFormat.setIndent("    ");

            FileOutputStream file = new FileOutputStream(fileName);
            xml = new XMLWriter(file, xmlFormat);
            xml.write(doucment);
            System.out.println("\nselector文件生成成功：" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (xml != null) try {
                xml.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 创建按下状态的节点
     *
     * @param state 0：默认 1：按下 2：不可点击
     */
    private void createItem(Element root, String solidColor, String strokeColor, int state) {
        //按下状态
        Element item = root.addElement("item ");
        switch (state) {
            case 0:
                item.addAttribute("android:state_pressed", "false");
                break;
            case 1:
                item.addAttribute("android:state_pressed", "true");
                break;
            case 2:
                item.addAttribute("android:state_enabled", "false");
                break;
            default:
                break;
        }
        Element shape = item.addElement("shape");

        shape.addElement("solid").addAttribute("android:color", "#" + solidColor);
        shape.addElement("stroke").addAttribute("android:color", "#" + strokeColor).addAttribute("android:width", "1dp");
        if (radius > 0)
            shape.addElement("corners").addAttribute("android:radius", radius + "dp");
        if (padding > 0)
            shape.addElement("padding")
                    .addAttribute("android:bottom", padding + "dp")
                    .addAttribute("android:left", padding + "dp")
                    .addAttribute("android:right", padding + "dp")
                    .addAttribute("android:top", padding + "dp");
    }
}
