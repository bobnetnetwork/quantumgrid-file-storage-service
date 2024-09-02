package network.bobnet.quantumgrid.file_storage_service.service;

import lombok.AllArgsConstructor;
import network.bobnet.quantumgrid.file_storage_service.entity.StorageConfig;
import network.bobnet.quantumgrid.file_storage_service.enums.StorageType;
import network.bobnet.quantumgrid.file_storage_service.repository.StorageConfigRepository;
import network.bobnet.quantumgrid.file_storage_service.service.impl.LocalStorageService;
import network.bobnet.quantumgrid.file_storage_service.service.impl.MinIOStorageService;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class StorageServiceFactory {

    private StorageConfigRepository storageConfigRepository;

    private LocalStorageService localStorageService;

    private MinIOStorageService minIOStorageService;

    public StorageService getStorageService() {
        StorageConfig config = storageConfigRepository.findById("default").orElseThrow(
                () -> new RuntimeException("Storage configuration not found"));

        StorageType storageType = config.getStorageType();

        return switch (storageType) {
            case LOCAL -> localStorageService;
            case MINIO -> minIOStorageService;
            default -> throw new IllegalArgumentException("Invalid storage type: " + storageType);
        };
    }
}
