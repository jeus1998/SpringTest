package spring.testcontainers.para;

import java.util.*;

public record Note(
     UUID noteId,
     String title,
     String content,
     Set<Tag> tags
)
{
    public Note(UUID newId){
        this(newId, null, null, null);
    }

}
