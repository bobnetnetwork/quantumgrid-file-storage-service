package network.bobnet.quantumgrid.file_storage_service.controller;

import lombok.AllArgsConstructor;
import network.bobnet.quantumgrid.file_storage_service.entity.FileMetadata;
import network.bobnet.quantumgrid.file_storage_service.service.FileMetadataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/metadata")
public class FileMetadataController {

    private FileMetadataService fileMetadataService;

    @GetMapping
    public ResponseEntity<List<FileMetadata>> getAllMetadata() {
        List<FileMetadata> metadataList = fileMetadataService.getAllMetadata();
        return ResponseEntity.ok(metadataList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileMetadata> getMetadataById(@PathVariable String id) {
        Optional<FileMetadata> metadata = fileMetadataService.getMetadataById(id);
        return metadata.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FileMetadata> saveMetadata(@RequestBody FileMetadata metadata) {
        FileMetadata savedMetadata = fileMetadataService.saveMetadata(metadata);
        return ResponseEntity.ok(savedMetadata);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMetadata(@PathVariable String id) {
        fileMetadataService.deleteMetadata(id);
        return ResponseEntity.ok("Metadata deleted successfully.");
    }
}
