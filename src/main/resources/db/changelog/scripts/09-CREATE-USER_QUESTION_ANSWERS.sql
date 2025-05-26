CREATE TABLE user_question_answers (
    student_id BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    answer_options_id BIGINT,
    answer_text TEXT,
    answered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (student_id, question_id),

    FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE,
    FOREIGN KEY (answer_options_id) REFERENCES answer_options(id) ON DELETE CASCADE,

    CHECK (
        (answer_options_id IS NOT NULL AND answer_text IS NULL)
        OR
        (answer_options_id IS NULL AND answer_text IS NOT NULL)
    )
);
