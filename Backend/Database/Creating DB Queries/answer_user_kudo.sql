DROP TABLE IF EXISTS answer_user_kudo;

CREATE TABLE IF NOT EXISTS answer_user_kudo (
	answer_id BIGINT NOT NULL,
	user_id BIGINT NOT NULL,
	PRIMARY KEY (answer_id, user_id),
	CONSTRAINT answer_user_kudo_id_fkey FOREIGN KEY (user_id)
		REFERENCES "user" (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT answer_user_kudo_question_id_fkey FOREIGN KEY (answer_id)
		REFERENCES answer (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);