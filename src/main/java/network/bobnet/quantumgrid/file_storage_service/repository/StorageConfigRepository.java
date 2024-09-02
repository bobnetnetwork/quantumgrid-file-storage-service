package network.bobnet.quantumgrid.file_storage_service.repository;

import network.bobnet.quantumgrid.file_storage_service.entity.StorageConfig;
import network.bobnet.quantumgrid.file_storage_service.enums.StorageType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageConfigRepository extends MongoRepository<StorageConfig, String> {

    StorageConfig findByStorageType(StorageType storageType);
}
