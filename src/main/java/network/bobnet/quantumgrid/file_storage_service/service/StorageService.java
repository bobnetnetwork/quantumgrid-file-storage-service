package network.bobnet.quantumgrid.file_storage_service.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface StorageService {

    String uploadFile(MultipartFile file);

    InputStream downloadFile(String fileName);

    void deleteFile(String fileName);
}
