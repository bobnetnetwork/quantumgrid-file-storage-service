package network.bobnet.quantumgrid.file_storage_service.config;

import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import network.bobnet.quantumgrid.commons.exceptions.NotFoundException;
import network.bobnet.quantumgrid.file_storage_service.entity.StorageConfig;
import network.bobnet.quantumgrid.file_storage_service.enums.StorageType;
import network.bobnet.quantumgrid.file_storage_service.repository.StorageConfigRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class MinIOConfig {

    private final StorageConfigRepository storageConfigRepository;

    @Bean
    public MinioClient minioClient() {
        StorageConfig config = storageConfigRepository.findByStorageType(StorageType.MINIO);
        if (config == null) {
            throw new NotFoundException("MinIO storage configuration not found in the database.");
        }

        return MinioClient.builder()
                .endpoint(config.getEndpointUrl())
                .credentials(config.getAccessKey(), config.getSecretKey())
                .build();
    }
}
