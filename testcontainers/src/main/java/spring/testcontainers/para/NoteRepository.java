package spring.testcontainers.para;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class NoteRepository {

    private final JdbcClient jdbcClient;

    public NoteRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void addNote(Note newNote, UUID newId) {
        var sql = """
                  insert into para_note (note_id, title, content)
                  values (:noteId, :title, :content)
                  """;

        jdbcClient.sql(sql)
                .param("noteId", newId.toString())
                .param("title", newNote.title())
                .param("content", newNote.content())
                .update();
    }

    public Optional<Note> getNote(Note note) {
        var sql = """
                  select note_id, title, content
                  from para_note
                  where note_id = :noteId
                  """;

        return jdbcClient.sql(sql)
                .param("noteId", note.noteId().toString())
                .query(noteRowMapper()).optional();
    }

    private RowMapper<Note> noteRowMapper() {
        return (rs, idx) -> new Note(
                UUID.fromString(rs.getString("note_id")),
                rs.getString("title"),
                rs.getString("content"),
                null
        );
    }

}
