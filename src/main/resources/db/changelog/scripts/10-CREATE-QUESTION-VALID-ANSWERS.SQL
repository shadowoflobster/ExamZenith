CREATE TABLE question_valid_answers(
    question_id BIGINT NOT NULL,
    answer_options_id BIGINT NOT NULL,

    PRIMARY KEY (question_id, answer_options_id),

    FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE,
    FOREIGN KEY (answer_options_id) REFERENCES answer_options(id) ON DELETE CASCADE
)