package com.littlenut.worktool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zcsm on 2017/8/3.
 */
public class configfile {
    public static Properties properties = new Properties();
    public static final String propName = "file.properties";
    public static final List<String> keyWordsList = new ArrayList<String>();
    public static final Map<String, String> keyWordsMap = new HashMap<String, String>();


    public static void setFileRule() {
        try {
            System.out.println("setFileRule::propName=[" + propName + "]");
            properties.load(ClassLoader.getSystemResourceAsStream(propName));
            String keyWord = properties.getProperty("keyword");
            System.out.println("setFileRule::keyWord=[" + keyWord + "]");
            if (keyWord != null || "".equals(keyWord)) {
                String[] keyworkarrey = keyWord.split(",");
                for (String word : keyworkarrey) {
                    String[] boss = word.split(":");
                    System.out.println("setFileRule::boss[0]=[" + boss[0] + "]");
                    System.out.println("setFileRule::boss[1]=[" + boss[1] + "]");
                    keyWordsList.add(boss[0]);
                    keyWordsMap.put(boss[0], boss[1]);
                }
            } else {
                throw new NullPointerException();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getInFileName() {
        System.out.println("getInFileName::name=[" + properties.getProperty("infilename") + "]");
        return System.getProperty("APP_HOME").concat(properties.getProperty("infilename"));
    }

    public static ArrayList<String> getKeyWordsList() {
        ArrayList<String> list = new ArrayList<String>();
        if (keyWordsList != null || keyWordsList.size() > 0) {
            list.addAll(keyWordsList);
        }
        return list;
    }

    public static Map<String, String> getKeyWordsMap() {
        Map<String, String> map = new HashMap<String, String>();
        if (keyWordsMap != null || keyWordsMap.size() > 0) {
            map.putAll(keyWordsMap);
        }
        return map;
    }

    public static String getOutFileName() {
        System.out.println("getOutFileName::name=[" + System.getProperty("APP_HOME").concat(properties.getProperty("outfilename")) + "]");
        return System.getProperty("APP_HOME").concat(properties.getProperty("outfilename"));
    }
}
