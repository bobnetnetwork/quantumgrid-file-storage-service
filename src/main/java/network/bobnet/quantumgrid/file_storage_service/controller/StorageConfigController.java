package network.bobnet.quantumgrid.file_storage_service.controller;

import lombok.AllArgsConstructor;
import network.bobnet.quantumgrid.file_storage_service.entity.StorageConfig;
import network.bobnet.quantumgrid.file_storage_service.repository.StorageConfigRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/storage-config")
public class StorageConfigController {

    private final StorageConfigRepository storageConfigRepository;

    @GetMapping("/{id}")
    public ResponseEntity<StorageConfig> getStorageConfig(@PathVariable String id) {
        StorageConfig config = storageConfigRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Storage configuration not found with id: " + id));
        return ResponseEntity.ok(config);
    }

    @GetMapping
    public ResponseEntity<List<StorageConfig>> getAllStorageConfigs() {
        List<StorageConfig> configs = storageConfigRepository.findAll();
        return ResponseEntity.ok(configs);
    }

    @PostMapping
    public ResponseEntity<StorageConfig> createOrUpdateStorageConfig(@RequestBody StorageConfig storageConfig) {
        StorageConfig savedConfig = storageConfigRepository.save(storageConfig);
        return ResponseEntity.ok(savedConfig);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStorageConfig(@PathVariable String id) {
        storageConfigRepository.deleteById(id);
        return ResponseEntity.ok("Storage configuration deleted successfully.");
    }
}
