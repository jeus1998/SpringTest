package spring.test;

import spring.test.model.Note;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import org.hamcrest.MatcherAssert;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import static org.assertj.core.api.BDDAssertions.then;

public class AssertJTest {

    /**
     * org.hamcrest.MatcherAssert
     * org.hamcrest.Matchers.*
     */
    @Test
    public void usingMatchers(){
        MatcherAssert.assertThat("foo", equalTo("foo"));
    }

    /**
     * AssertJ 특징
     * 메서드 체이닝
     * fluent API
     * org.assertj.core.api.Assertions.assertThat
     */
    @Test
    public void usingAssertJ(){
        assertThat("foo").isEqualTo("foo");
    }

    /**
     * BDD
     * given / when / then
     * org.assertj.core.api.BDDAssertions.then;
     */
    @Test
    public void bddStyleEntry(){
        then("foo").isEqualTo("foo");
    }

    @Test
    public void noteTest(){
        var note = new Note(
                UUID.randomUUID(),
                "title",
                "content",
                true
        );

        assertThat(note.title())
                .isNotNull()
                .contains("it")
                .isEqualTo("title");

        assertThat(note).isInstanceOf(Note.class);
    }

    /**
     * Description
     */
    @Test
    public void assertJDescription(){
        var note = new Note(
                UUID.randomUUID(),
                "title",
                "content",
                true
        );
        assertThat(note)
                .as("check instance : %s", note.title())
                .isInstanceOf(Note.class);
    }

    /**
     * exception
     */
    @Test
    public void assertJException(){
        var note = new Note(
                UUID.randomUUID(),
                "title",
                "content",
                true
        );

        assertThatThrownBy(() -> note.removeNote(UUID.randomUUID()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("노트가 없습니다.")
                .message();

        assertThatNoException().isThrownBy(() -> note.removeNote(note.noteId()));
    }

}
