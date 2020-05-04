DROP TABLE IF EXISTS seen_question;

CREATE TABLE IF NOT EXISTS seen_question (
	user_id BIGINT NOT NULL,
	question_id BIGINT NOT NULL,
	PRIMARY KEY (user_id, question_id),
	CONSTRAINT seen_question_user_id_fkey FOREIGN KEY (user_id)
		REFERENCES "user" (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT seen_question_question_id_fkey FOREIGN KEY (question_id)
		REFERENCES question (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);