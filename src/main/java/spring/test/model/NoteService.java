package spring.test.model;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static spring.test.model.Note.NoteCase;

@Service
public class NoteService implements NoteCase {

    private final NoteRepositoryPort repositoryPort;

    public NoteService(NoteRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Optional<Note> addNote(Note emptyIdNote, UUID newNoteId) {
        repositoryPort.addNote(emptyIdNote, newNoteId);
        return repositoryPort.getNote(newNoteId);
    }

    @Override
    public Note getNote() {
        return null;
    }
}
