package ru.otus.db;

import java.io.IOException;

public class ResourceReaderCreationException extends RuntimeException {
    public ResourceReaderCreationException(String message, IOException e) {
        super(message, e);
    }
}
