DROP TABLE IF EXISTS question_interest;

CREATE TABLE IF NOT EXISTS question_interest (
	question_id BIGINT NOT NULL,
	interest_id BIGINT NOT NULL,
	PRIMARY KEY (question_id, interest_id),
	CONSTRAINT question_interest_interest_id_fkey FOREIGN KEY (interest_id)
		REFERENCES interest (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT question_interest_question_id_fkey FOREIGN KEY (question_id)
		REFERENCES question (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);