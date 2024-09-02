package network.bobnet.quantumgrid.file_storage_service.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import network.bobnet.quantumgrid.file_storage_service.entity.StorageConfig;
import network.bobnet.quantumgrid.file_storage_service.enums.StorageType;
import network.bobnet.quantumgrid.file_storage_service.repository.StorageConfigRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class AppInitializer implements CommandLineRunner {

    private final StorageConfigRepository storageConfigRepository;

    @Override
    public void run(String... args) {
        initializeDefaultStorageConfig();
    }

    private void initializeDefaultStorageConfig() {
        if (storageConfigRepository.findById("default").isEmpty()) {
            StorageConfig defaultConfig = new StorageConfig();
            defaultConfig.setId("default");
            defaultConfig.setStorageType(StorageType.LOCAL);
            defaultConfig.setLocalDirectory(System.getProperty("user.home") + "/filestorage");

            storageConfigRepository.save(defaultConfig);
            log.info("Default storage configuration created successfully.");
        } else {
            log.info("Default storage configuration already exists.");
        }
    }
}
