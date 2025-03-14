package spring.test.model;

import java.util.UUID;

public record Note (
    UUID noteId,
    String title,
    String contents,
    boolean isNew
)
{
    public int removeNote(UUID noteId){
        if(!this.noteId.equals(noteId)){
            throw new RuntimeException("노트가 없습니다.");
        }
        return 1;
    }
}
