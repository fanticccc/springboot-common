package com.example.test.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Author: SongJunBao@pukkasoft.cn
 * @Date: 2023/4/26 15:02
 * @Package: com.example.test.file
 * @Description: -TODO
 */
public class FileTest {

    public static void main(String[] args) throws IOException {
//        FileOutputStream op = null;
//        try {
//            File file = new File("E:\\java\\aa\\wok.txt");
//            if (!file.isFile()){
//                File file1 = file.getParentFile();
//                file1.mkdirs();
//            }
//            op = new FileOutputStream(file);
//            String msg = " hello world";
//            byte[] bytes = msg.getBytes();
//            op.write(bytes);
//            op.flush();
//        }catch (Exception e){
//            assert op != null;
//            op.close();
//        }
        List<String> list = null;
        System.out.println(list.size());
    }
}
