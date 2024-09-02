package network.bobnet.quantumgrid.file_storage_service.exception;

import network.bobnet.quantumgrid.commons.exceptions.AbstractApplicationException;

public class FileOperationException extends AbstractApplicationException {

    public FileOperationException(final String message) {
        super(message);
    }

    public FileOperationException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
