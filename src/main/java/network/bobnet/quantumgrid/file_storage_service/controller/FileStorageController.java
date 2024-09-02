package network.bobnet.quantumgrid.file_storage_service.controller;

import lombok.AllArgsConstructor;
import network.bobnet.quantumgrid.file_storage_service.service.StorageService;
import network.bobnet.quantumgrid.file_storage_service.service.StorageServiceFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@AllArgsConstructor
@RestController
@RequestMapping("/api/files")
public class FileStorageController {

    private final StorageServiceFactory storageServiceFactory;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        StorageService storageService = storageServiceFactory.getStorageService();
        String response = storageService.uploadFile(file);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) {
        StorageService storageService = storageServiceFactory.getStorageService();
        InputStream file = storageService.downloadFile(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(new InputStreamResource(file));
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        StorageService storageService = storageServiceFactory.getStorageService();
        storageService.deleteFile(fileName);
        return ResponseEntity.ok("File deleted successfully: " + fileName);
    }
}
