package com.itzyf.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

/**
 * Created by chenglin on 16-8-3.
 */
public class GlobalConfig implements Observer {

    private static Logger LOGGER = LoggerFactory.getLogger(GlobalConfig.class);

    private static GlobalConfig instance = null;

    private Properties p = new Properties();

    /*
 * 加载actionclass.properties文件
 * fwh date:2009-08-25
 */
    public static GlobalConfig getConfig() {
        if (instance == null) {
            // 创建配置对象时候，加载配置文件信息
            instance = new GlobalConfig();
        }
        return instance;
    }


    private GlobalConfig()
    {
        load();
    }


    @Override
    public void update(Observable o, Object arg) {
        LOGGER.info("============reload global_config.properties===============");
        load();
    }

    private void load() {
        InputStream in = null;
        try {
            in=getClass().getClassLoader().getResourceAsStream("global-config.properties");
            p.load(in);
        } catch (Exception e) {
            LOGGER.error("Load actionclass.properties Error!", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    LOGGER.error("Close InputStream Error!", e);
                }
            }
        }
    }

    public String getConfigValue(String configName) {
        return p.getProperty(configName);
    }
}
