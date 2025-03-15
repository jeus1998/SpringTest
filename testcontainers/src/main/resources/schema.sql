CREATE TABLE IF NOT EXISTS para_tag (
    tag_id BIGINT NOT NULL AUTO_INCREMENT,
    tag VARCHAR(36),
    PRIMARY KEY (tag_id)
);

CREATE TABLE IF NOT EXISTS para_note (
    note_id VARCHAR(36) NOT NULL,
    title VARCHAR(255),
    content TEXT,
    PRIMARY KEY (note_id)
) COMMENT='노트';

ALTER TABLE para_note MODIFY COLUMN note_id VARCHAR(36) COMMENT 'id';
ALTER TABLE para_note MODIFY COLUMN title VARCHAR(255) COMMENT '제목';
ALTER TABLE para_note MODIFY COLUMN content TEXT COMMENT '노트 내용';

CREATE TABLE IF NOT EXISTS para_note_tag (
    note_id VARCHAR(36) NOT NULL,
    tag_id BIGINT NOT NULL,
    PRIMARY KEY (note_id, tag_id),
    CONSTRAINT fk_para_note_tag_note_id FOREIGN KEY (note_id) REFERENCES para_note(note_id),
    CONSTRAINT fk_para_note_tag_tag_id FOREIGN KEY (tag_id) REFERENCES para_tag(tag_id)
);
