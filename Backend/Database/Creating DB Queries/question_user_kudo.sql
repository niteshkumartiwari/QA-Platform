DROP TABLE IF EXISTS question_user_kudo;

CREATE TABLE IF NOT EXISTS question_user_kudo (
	question_id BIGINT NOT NULL,
	user_id BIGINT NOT NULL,
	PRIMARY KEY (question_id, user_id),
	CONSTRAINT question_user_kudo_id_fkey FOREIGN KEY (user_id)
		REFERENCES "user" (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT question_user_kudo_question_id_fkey FOREIGN KEY (question_id)
		REFERENCES question (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);