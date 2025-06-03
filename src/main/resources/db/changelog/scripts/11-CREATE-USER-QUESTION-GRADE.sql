CREATE TABLE user_question_grade(
    student_id BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    is_true BOOLEAN,

    PRIMARY KEY (student_id, question_id),

    FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
)