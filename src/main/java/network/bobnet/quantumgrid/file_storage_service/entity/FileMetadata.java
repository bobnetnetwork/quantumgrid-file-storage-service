package network.bobnet.quantumgrid.file_storage_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "file_metadata")
public class FileMetadata {

    @Id
    private String fileName;

    private String title;
    private String description;
    private String author;
    private List<String> keywords;
    private LocalDateTime uploadDate;
    private Long fileSize;
    private String fileType;
}
