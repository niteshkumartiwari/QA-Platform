DROP TABLE IF EXISTS comment_question;

CREATE TABLE IF NOT EXISTS comment_question (
	id BIGSERIAL PRIMARY KEY,
	content VARCHAR(500) NOT NULL,
	upkudo BIGINT DEFAULT 0,
	question_id BIGINT NOT NULL,
	replied_by BIGINT NOT NULL,
	created_on TIMESTAMP DEFAULT (Now() AT TIME ZONE 'utc'),
	CONSTRAINT comment_question_question_id_fkey FOREIGN KEY (question_id)
		REFERENCES question (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT comment_question_replied_by_fkey FOREIGN KEY (replied_by)
		REFERENCES "user" (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);