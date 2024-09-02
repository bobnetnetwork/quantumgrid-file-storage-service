package network.bobnet.quantumgrid.file_storage_service.service;

import lombok.AllArgsConstructor;
import network.bobnet.quantumgrid.file_storage_service.entity.FileMetadata;
import network.bobnet.quantumgrid.file_storage_service.repository.FileMetadataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FileMetadataService {

    private final FileMetadataRepository fileMetadataRepository;

    public List<FileMetadata> getAllMetadata() {
        return fileMetadataRepository.findAll();
    }

    public Optional<FileMetadata> getMetadataById(String id) {
        return fileMetadataRepository.findById(id);
    }

    public FileMetadata saveMetadata(FileMetadata metadata) {
        return fileMetadataRepository.save(metadata);
    }

    public void deleteMetadata(String id) {
        fileMetadataRepository.deleteById(id);
    }
}
