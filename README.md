# File Storage Service

The **File Storage Service** handles file uploads and storage in the QuantumGrid platform.

## Features

- Upload and store files (images, documents, etc.)
- Manage file metadata
- Serve files for client consumption
- Integration with cloud storage (e.g., AWS S3, Azure Blob)

## Technology Stack

- **Java**: Programming language
- **Spring Boot**: Microservice framework
- **MongoDB**: Document database for storing file metadata
- **Local/Cloud Storage**: For actual file storage

## Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven** for build automation
- **MongoDB** installed and running

### Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/bobnetnetwork/quantumgrid-file-storage-service.git
    cd quantumgrid-file-storage-service
    ```

2. Configure the database and storage connection in `src/main/resources/application.properties`:
    ```
    spring.data.mongodb.uri=mongodb://localhost:27017/quantumgrid
    storage.path=/your/local/storage/path
    ```

3. Build the application:
    ```bash
    mvn clean install
    ```

4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

### API Endpoints

- `POST /api/files` - Upload a new file
- `GET /api/files/{id}` - Get file details and download link
- `DELETE /api/files/{id}` - Delete a file

## Contributing

Please read the [CONTRIBUTING.md](https://github.com/bobnetnetwork/quantumgrid/blob/main/CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests.

## License

This project is licensed under the GPL-3.0 license - see the [LICENSE.md](https://github.com/bobnetnetwork/quantumgrid/blob/main/LICENSE.md) file for details.
