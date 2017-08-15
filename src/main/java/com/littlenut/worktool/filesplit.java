package com.littlenut.worktool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Hello world!
 */
public class filesplit {
    private static ArrayList<String> kwList;
    private static Map<String, String> kwMap;
    private static int listnow;
    private static int keyListNum;

    public static void main(String[] args) {
        //etc file
        configfile.setFileRule();
        writefile.init(configfile.getOutFileName());
        String fileName = configfile.getInFileName();
        kwList = configfile.getKeyWordsList();
        kwMap = configfile.getKeyWordsMap();
        keyListNum = kwList.size();
        listnow = 0;

        //loop
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            int readLine = 0;
            int lineNum = 0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                lineNum++;
                if (lineNum > readLine) {
                    readLine = checkfile(tempString, lineNum);
                    readLine = readLine + lineNum;
                } else {
                    writefile.write(tempString, lineNum);
                }
            }
            reader.close();
            writefile.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static int checkfile(final String filestring, int lineNumber) {
        int wtLine = 0;
        if (filestring.contains(kwList.get(listnow))) {
            wtLine = Integer.valueOf(kwMap.get(kwList.get(listnow)));
            writefile.write(filestring, lineNumber);
            listnow++;
            if (listnow == keyListNum) {
                listnow = 0;
            }
        }
        return wtLine;
    }

}