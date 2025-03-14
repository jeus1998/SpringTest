package spring.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.test.model.Note;
import spring.test.model.NoteRepositoryPort;
import spring.test.model.NoteService;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// mockito 확장
@ExtendWith(MockitoExtension.class)
public class MockitoTest {

    // Mock 객체를 주입할 대상 객체
    @InjectMocks
    NoteService noteService;

    // 대상 객체에 mock 처리할 DI된 객체
    @Mock
    NoteRepositoryPort repositoryPort;

    @Test
    public void noteTest(){
        // given
        var noteId = UUID.randomUUID();
        var note = new Note(
            null,
            "title",
        "content",
            true
        );

        // when
        var findNote = new Note(
                noteId,
                "title",
                "content",
                true
        );

        // when: stub
        when(repositoryPort.getNote(any(UUID.class)))
                .thenReturn(Optional.of(findNote));

        // then
        var createdNote = noteService.addNote(note, noteId);
        assertThat(createdNote.get()).isInstanceOf(Note.class);
        assertThat(createdNote.get().noteId()).isEqualTo(noteId);

        // then: mock(행위)
        verify(repositoryPort, times(1))
                .addNote(any(Note.class), any(UUID.class));

    }
}
