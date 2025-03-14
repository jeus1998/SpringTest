package spring.test.model;

import java.util.Optional;
import java.util.UUID;

public interface NoteRepositoryPort {
    void addNote(Note emptyIdNote, UUID noteId);
    Optional<Note> getNote(UUID noteId);
}
