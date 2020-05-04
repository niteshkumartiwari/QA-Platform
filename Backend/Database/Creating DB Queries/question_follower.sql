DROP TABLE IF EXISTS question_follower;

CREATE TABLE IF NOT EXISTS question_follower (
	user_id BIGINT NOT NULL,
	question_id BIGINT NOT NULL,
	PRIMARY KEY (user_id, question_id),
	CONSTRAINT question_follower_user_id_fkey FOREIGN KEY (user_id)
		REFERENCES "user" (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT question_follower_question_id_fkey FOREIGN KEY (question_id)
		REFERENCES question (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);