package com.testing.configuration;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigProvider {

    private static Config readConfig() {
        return ConfigFactory.parseResources("config.json");
    }

    public static String getBaseUrl(){
        return readConfig().getString("baseUrl");
    }
}
