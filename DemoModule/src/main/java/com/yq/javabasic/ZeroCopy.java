package com.yq.javabasic;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: JavaDemoRep
 * @description: 零拷贝
 * @author: Yuqing
 * @create: 2023-10-03 10:51
 **/
public class ZeroCopy {

    public static void main(String[] args) throws IOException {
        File file = new File("E:\\FutureArchitect\\项目\\JavaDemoRep\\files\\DemoModule\\zeroCopy.txt");
        // 1.mmap 方式
        //     RandomAccessFile 打开文件
        final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        //     getChannel() 方法获取文件通道；调用 map() 方法将文件映射到内存中：第一个参数指定映射模式（READ_WRITE 表示可读可写）、第二个参数指定映射的起始位置、第三个参数指定映射的长度
        final MappedByteBuffer map = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_WRITE,0,2048);
        //     将内容写入文件
        map.put("mmap content".getBytes());
        //     关闭文件
        randomAccessFile.close();

        // 2.sendfile 方式
        try (FileInputStream inputStream = new FileInputStream(file)){
            FileChannel channel = inputStream.getChannel();

            FileOutputStream outputStream = new FileOutputStream("E:\\FutureArchitect\\项目\\JavaDemoRep\\files\\DemoModule\\zeroCopy_copy.txt");
            FileChannel outChannel = outputStream.getChannel();
            //    transferTo 方式，底层调用的还是 sendfile
            channel.transferTo(0, file.length(), outChannel);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

}
