package com.example.clientproject.web.restControllers;

import com.example.clientproject.exceptions.ForbiddenErrorException;
import com.example.clientproject.service.Utils.RemoveRedundantFiles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
public class FileUpload {

    private RemoveRedundantFiles removeFiles;

    public FileUpload(RemoveRedundantFiles removeFile){
        removeFiles = removeFile;
    }

    String UPLOAD_FOLDER = "./src/main/resources/static/imgs/uploaded/";
    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {

        removeFiles.deleteFiles();

        if (file.isEmpty()) {
            throw new ForbiddenErrorException("No file");
        }
        String cleansedName = "Error";
        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            UUID uuid = UUID.randomUUID();
            String uuidName = uuid.toString() + "." + ext;
            cleansedName = uuidName.replaceAll("[^a-zA-Z0-9.-]|[-]", "_");
            Path path = Paths.get(UPLOAD_FOLDER + cleansedName);
            Files.write(path, bytes);

            return cleansedName;

        } catch (IOException e) {
            //e.printStackTrace();
        }

        return cleansedName;
    }
}
