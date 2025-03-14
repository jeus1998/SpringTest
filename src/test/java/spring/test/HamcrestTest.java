package spring.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.test.model.Note;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestTest {

    Note note;

    @BeforeEach
    public void setup() {
        note = new Note(
                UUID.randomUUID(),
                "title",
                "content",
                true);
    }

    @Test
    public void basic_junit(){
        assertEquals("title", note.title());
    }

    /**
     * MatcherAssert.assertThat 보다는 AssertJ.assertThat 많이 사용한다.
     */
    @Test
    public void basic_hamcrest(){
        assertThat(note.title(), equalTo("title"));
    }

    @Test
    public void coreMatchers(){
        assertThat(note.title(), anything());
    }

    @Test
    public void logicalMatchers(){
        assertThat(note.title(), allOf(
                equalTo("title"),
                hasLength(5)
        ));
    }

    static class Person {
        private final String name;
        public Person(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    /**
     * Hamcrest hasProperty(): 특정 필드가 Bean 규칙을 따르는지 확인
     */
    @Test
    public void beanPropertyTest(){
        Person person = new Person("John");
        assertThat(person, hasProperty("name"));
        assertThat(note, not(hasProperty("title")));
    }

    @Test
    public void sugar(){
        assertThat(note.title(), equalTo("title"));
        assertThat(note.title(), is("title"));
    }
}
