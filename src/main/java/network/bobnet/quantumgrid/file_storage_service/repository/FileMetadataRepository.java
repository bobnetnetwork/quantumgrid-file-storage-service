package network.bobnet.quantumgrid.file_storage_service.repository;

import network.bobnet.quantumgrid.file_storage_service.entity.FileMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMetadataRepository extends MongoRepository<FileMetadata, String> {
}
