package com.enduser.consumer.Store;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.enduser.consumer.Constant.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileSave {

    @Value("${application.bucket.name}")
    private String bucketname;

    @Value("${file.storage.path}")
    private String fileStoragePath;

    @Autowired
    private AmazonS3 s3client;
    /*
    @Value("${file.storage.path}")
    private String fileStoragePath;

    public void storeFile(MultipartFile file) throws IOException {

        Path storageDirectory= Paths.get(fileStoragePath);
        if(!Files.exists(storageDirectory))
            Files.createDirectories(storageDirectory);

        String fileName=file.getOriginalFilename();
        Path targetLocation=storageDirectory.resolve(fileName);

        System.out.println("File transfered new location " +targetLocation);
    }

     */

    @KafkaListener(topics = AppConstant.FILE_TOPIC,groupId = AppConstant.GROUP_ID)
    public void updatedLocation(String value,byte[] pdfbytes) throws IOException {
        String filename="Consumed_file_"+System.currentTimeMillis();
        FileOutputStream fileOutputStream=new FileOutputStream(fileStoragePath+filename);
        fileOutputStream.write(pdfbytes);
        File uploadFile=new File(Paths.get(fileStoragePath+filename).toUri());
        uploadFile(uploadFile);
        System.out.println("file added to desktop");
    }



    public String uploadFile(File file)
    {
        //File fileobj=convertMultipartToFile(file);
        String fileName=System.currentTimeMillis()+"_"+file.getName();
        s3client.putObject(new PutObjectRequest(bucketname,fileName,file));
        file.delete();
        System.out.println("file has been uploded successfully "+fileName);

        return "file has been uploded successfully "+fileName;
    }

    /*
    private File convertMultipartToFile(MultipartFile file)
    {
        File convertedFile =new File(file.getOriginalFilename());
        try(FileOutputStream fos=new FileOutputStream(convertedFile)){
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return convertedFile;
    }

     */




}
