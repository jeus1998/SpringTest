package spring.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import spring.test.model.Note;
import java.util.*;
import java.util.function.*;

import static org.junit.jupiter.api.Assertions.*;
import static spring.test.JunitTest.Code.*;
import org.junit.jupiter.api.Test; // JUnit5

public class JunitTest {

    @Test
    public void basicUseJunitTest(){
        var note = new Note(
                UUID.randomUUID(),
                "title",
                "content",
                true);

        assertEquals("title", note.title());
        assertTrue(note.isNew());
    }

    @Test
    @DisplayName("에러 메시지 with supplier")
    public void messageSupplierTest(){
        var note = new Note(
                UUID.randomUUID(),
                "title",
                "content",
                false);
        assertFalse(note.isNew(), errorMessage.apply(NotNewNote));
    }

    @Test
    @DisplayName("throw Exception")
    public void exceptionTest(){
        var note = new Note(
                UUID.randomUUID(),
                "title",
                "content",
                false);
        assertThrows(RuntimeException.class, () -> note.removeNote(UUID.randomUUID()));
    }

    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {2025, 2026, 2027})
    public void parameterizedTest(int year){
        System.out.println(year);
    }

    private final Function<Code, Supplier<String>> errorMessage = code -> {
        return () -> code + " : " + code.getMessage();
    };

    enum Code {
        NotNewNote("새로운 노트가 아닙니다."),
        NotFound("노트가 없습니다.");
        private final String message;
        private Code(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }
}
