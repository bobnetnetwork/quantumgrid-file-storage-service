package network.bobnet.quantumgrid.file_storage_service.service.impl;

import io.minio.*;
import lombok.AllArgsConstructor;
import network.bobnet.quantumgrid.file_storage_service.entity.StorageConfig;
import network.bobnet.quantumgrid.file_storage_service.enums.StorageType;
import network.bobnet.quantumgrid.file_storage_service.exception.FileOperationException;
import network.bobnet.quantumgrid.file_storage_service.repository.StorageConfigRepository;
import network.bobnet.quantumgrid.file_storage_service.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@AllArgsConstructor
@Service
public class MinIOStorageService implements StorageService {

    private MinioClient minioClient;
    private StorageConfig storageConfig;

    @Autowired
    public MinIOStorageService(StorageConfigRepository storageConfigRepository) {
        this.storageConfig = storageConfigRepository.findByStorageType(StorageType.MINIO);

        this.minioClient = MinioClient.builder()
                .endpoint(storageConfig.getEndpointUrl())
                .credentials(storageConfig.getAccessKey(), storageConfig.getSecretKey())
                .build();
    }

    private void createBucketIfNotExists() throws Exception {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(storageConfig.getBucketName()).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(storageConfig.getBucketName()).build());
        }
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            createBucketIfNotExists();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(storageConfig.getBucketName())
                            .object(file.getOriginalFilename())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            return "File uploaded successfully: " + file.getOriginalFilename();
        } catch (Exception e) {
            throw new FileOperationException("Error uploading file to MinIO", e);
        }
    }

    @Override
    public InputStream downloadFile(String fileName) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(storageConfig.getBucketName())
                            .object(fileName)
                            .build()
            );
        } catch (Exception e) {
            throw new FileOperationException("Error downloading file from MinIO", e);
        }
    }

    @Override
    public void deleteFile(String fileName) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(storageConfig.getBucketName())
                            .object(fileName)
                            .build()
            );
        } catch (Exception e) {
            throw new FileOperationException("Error deleting file from MinIO", e);
        }
    }
}
