package network.bobnet.quantumgrid.file_storage_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import network.bobnet.quantumgrid.file_storage_service.enums.StorageType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "storage_config")
public class StorageConfig {

    @Id
    private String id;

    private StorageType storageType;
    private String endpointUrl;
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String localDirectory;
}
