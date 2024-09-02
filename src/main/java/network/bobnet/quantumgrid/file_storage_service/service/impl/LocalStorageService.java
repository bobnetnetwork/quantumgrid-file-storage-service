package network.bobnet.quantumgrid.file_storage_service.service.impl;

import network.bobnet.quantumgrid.file_storage_service.entity.StorageConfig;
import network.bobnet.quantumgrid.file_storage_service.enums.StorageType;
import network.bobnet.quantumgrid.file_storage_service.exception.FileOperationException;
import network.bobnet.quantumgrid.file_storage_service.repository.StorageConfigRepository;
import network.bobnet.quantumgrid.file_storage_service.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LocalStorageService implements StorageService {

    private String localDirectory;

    @Autowired
    public LocalStorageService(StorageConfigRepository storageConfigRepository) {
        StorageConfig config = storageConfigRepository.findById("default")
                .orElseThrow(() -> new RuntimeException("Default storage configuration not found"));

        if (StorageType.LOCAL.equals(config.getStorageType())) {
            this.localDirectory = config.getLocalDirectory();
        }
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            File targetFile = new File(localDirectory + File.separator + file.getOriginalFilename());
            try (FileOutputStream fos = new FileOutputStream(targetFile)) {
                fos.write(file.getBytes());
            }
            return targetFile.getAbsolutePath();
        } catch (IOException e) {
            throw new FileOperationException("Error uploading file to local storage", e);
        }
    }

    @Override
    public InputStream downloadFile(String fileName) {
        try {
            File file = new File(localDirectory + File.separator + fileName);
            return new FileInputStream(file);
        } catch (IOException e) {
            throw new FileOperationException("Error downloading file from local storage", e);
        }
    }

    @Override
    public void deleteFile(String fileName) {
        Path filePath = Paths.get(localDirectory, fileName);
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new FileOperationException("Error deleting file from local storage: " + e.getMessage(), e);
        }
    }
}
