package com.littlenut.worktool;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zcsm on 2017/8/4.
 */
public class writefile {
    private static FileOutputStream fileout;

    public static void init(String fileName) {
        try {
            fileout = new FileOutputStream(configfile.getOutFileName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void write(final String filestring, final int linenum) {
        try{
            String wt = String.valueOf(linenum) + " " + filestring;
            System.out.println("write::wt=[" + wt + "]");
            fileout.write(wt.getBytes());
            fileout.write("\r\n".getBytes());

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void close() throws IOException
    {
        fileout.close();
    }
}
