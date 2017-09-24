package com.foxleezh.basecomponent.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍 文件操作相关工具类
 */
public class FileUtils {
    private static final int IO_BUFFER_SIZE = 8 * 1024; // 8k
    private static final int BUFFER_SIZE = 2 * 1024;// 2K


    public static double getSize(File file) {
        //判断文件是否存在
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小，如果是文件则直接返回其大小
            if (!file.isFile()) {
                //获取文件大小
                File[] fl = file.listFiles();
                if (fl == null || fl.length <= 0) {
                    return 0;
                }
                double ss = 0;
                for (File f : fl)
                    ss += getSize(f);
                return ss;
            } else {
                double ss = (double) file.length() / 1024 / 1024;
                return ss;
            }
        } else {
            return 0.0;
        }
    }

    // 创建文件
    public static File createFile(String fileName) throws IOException {
        File file = new File(fileName);
        file.createNewFile();
        return file;
    }

    /**
     * 创建目录
     *
     * @param dirName
     * @return
     */
    public static File createDir(String dirName) {
        File dir = new File(dirName);
        dir.mkdir();
        return dir;
    }

    /**
     * 创建多层级目录
     *
     * @param dirName
     * @return
     */
    public static File createDirs(String dirName) {
        File dir = new File(dirName);
        dir.mkdirs();
        return dir;
    }

    /**
     * 判断文件是否存在
     *
     * @param fileName
     * @return
     */
    public static boolean isFileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    /**
     * 将文件内容转换为byte数组
     * @param filePath
     * @return
     */
    public static byte[] file2byte(String filePath)
    {
        byte[] buffer = null;
        try
        {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return buffer;
    }
}
