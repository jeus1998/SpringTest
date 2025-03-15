package spring.testcontainers.para;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(NoteRepository.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class NoteRepositoryTest {

    @Container
    @ServiceConnection
    private static MySQLContainer<?> mysql =
            new MySQLContainer<>(DockerImageName.parse("mysql:latest"));

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void noteTest(){
        var newNote = new Note(
            null,
            "java",
            "grammar",
            null
        );
        var newId = UUID.randomUUID();
        noteRepository.addNote(newNote, newId);

        Optional<Note> finedNote = noteRepository.getNote(new Note(newId));
        assertThat(finedNote.isPresent()).isTrue();
        assertThat(finedNote.get()).isInstanceOf(Note.class);
        assertThat(finedNote.get().noteId()).isEqualTo(newId);
        assertThat(finedNote.get().title()).isEqualTo("java");
    }
}