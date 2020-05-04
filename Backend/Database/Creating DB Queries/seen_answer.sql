DROP TABLE IF EXISTS seen_answer;

CREATE TABLE IF NOT EXISTS seen_answer (
	user_id BIGINT NOT NULL,
	answer_id BIGINT NOT NULL,
	PRIMARY KEY (user_id, answer_id),
	CONSTRAINT seen_answer_user_id_fkey FOREIGN KEY (user_id)
		REFERENCES "user" (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT seen_answer_answer_id_fkey FOREIGN KEY (answer_id)
		REFERENCES answer (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);