package com.szqz.util;

import java.io.*;
import java.util.Base64;

public class Base64Utils {

    public String writePicture(String file){
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(Base64.getEncoder().encode(data));
    }

    public void backToPicture(String base, String file){
        FileOutputStream write = null;
        try {
            write = new FileOutputStream(new File(file));
            byte[] decoderBytes = Base64.getDecoder().decode(base);
            write.write(decoderBytes);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
