package spring.test.model;

import java.util.UUID;

public record Note (
    UUID noteId,
    String title,
    String contents,
    boolean isNes
)
{ }
