package com.spring.Services.Imple_Class;

import com.spring.Services.Interface.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileserviceImpl implements FileService {
    private Logger logger= LoggerFactory.getLogger(FileserviceImpl.class);
    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
        String originalFileName= file.getOriginalFilename();
        logger.info("it is the file name{}", originalFileName);
        String randomFileName= UUID.randomUUID().toString();
        String extension= originalFileName.substring(originalFileName.lastIndexOf("."));
        logger.info("it is the extension{}", extension);
        String fileNameWithextension= randomFileName+extension;
        logger.info("fileNameWithextension{}", fileNameWithextension);
        String fullPathWithFileName= path+fileNameWithextension;
        logger.info("fullPathWithFileName{}",fullPathWithFileName);
        File folder= new File(path);

        if (!folder.exists()){
            folder.mkdirs();
        }
        //upload
        Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
        return fileNameWithextension;
    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {

        String fullPathname= path+File.separator+ name;
        InputStream inputStream= new FileInputStream(fullPathname);
        return inputStream;
    }
}
