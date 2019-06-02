package kr.ac.jejunu.sslab.gongdae.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileUploadService {
    public String uploadFile(@RequestParam MultipartFile vrImgUrl) throws IOException {
        String filePath = "/images/" + vrImgUrl.getOriginalFilename();
        File path =
                new File("images/" + vrImgUrl.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(vrImgUrl.getBytes());
        bufferedOutputStream.close();
        return filePath;
    }
}