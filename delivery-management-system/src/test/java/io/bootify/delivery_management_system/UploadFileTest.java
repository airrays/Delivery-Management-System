package io.bootify.delivery_management_system;

import org.junit.Test;

public class UploadFileTest {
    @Test
    public void test1(){
        String fileName="abc.jpg";
        String suffix=fileName.substring(fileName.lastIndexOf("."));
        System.out.println(suffix);
    }
}
