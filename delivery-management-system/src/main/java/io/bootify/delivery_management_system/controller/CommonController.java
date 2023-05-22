package io.bootify.delivery_management_system.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${delivery-management-system.path}")
    private String basePath;
    /***
     * File Upload. Form--> post enctpye="multipart/form-data type="file"
     * Server-side use Apache commons-fileupload,commons-io
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<?> upload(MultipartFile file){
        //file is a temp file, require save in target place, else the temp file will be deleted after request
        log.info("file:{}",file.toString());
        //original file name
        String originalFilename=file.getOriginalFilename(); //abc.jpg
        String suffix=originalFilename.substring(originalFilename.lastIndexOf(".")); //substring .jpg
        //uuid for random file name, in case file overlap
        String fileName= UUID.randomUUID().toString()+suffix; //adsa.jpg
        File dir=new File(basePath);
        if(!dir.exists()){ //check if folder exist
            dir.mkdirs();
        }
        try{
            file.transferTo(new File(basePath));
        }catch(IOException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /***
     * handleAvatoarSuccess(response,file,fileList){this.imageUrl="/common/download?name=${response.data}}
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        try{
            FileInputStream fileInputStream=new FileInputStream(new File(basePath+name));
            ServletOutputStream outputStream=response.getOutputStream();
            int len=0;
            byte[] bytes=new byte[1024];
            while((len=fileInputStream.read(bytes))!=-1){  //read and put into {bytes}
                outputStream.write(bytes,0,len);
                outputStream.flush(); //refresh
            }
            outputStream.close();
            fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
