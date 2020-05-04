DROP TABLE IF EXISTS comment_answer;

CREATE TABLE IF NOT EXISTS comment_answer (
	id BIGSERIAL PRIMARY KEY,
	content VARCHAR(500) NOT NULL,
	upkudo BIGINT DEFAULT 0,
	answer_id BIGINT NOT NULL,
	replied_by BIGINT NOT NULL,
	created_on TIMESTAMP DEFAULT (Now() AT TIME ZONE 'utc'),
	CONSTRAINT comment_answer_answer_id_fkey FOREIGN KEY (answer_id)
		REFERENCES answer (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT comment_answer_replied_by_fkey FOREIGN KEY (replied_by)
		REFERENCES "user" (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);