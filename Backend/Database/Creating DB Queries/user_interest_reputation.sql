DROP TABLE IF EXISTS user_interest_reputation;

CREATE TABLE IF NOT EXISTS user_interest_reputation (
	user_id BIGINT NOT NULL,
	interest_id BIGINT NOT NULL,
	reputation BIGINT DEFAULT 0,
	PRIMARY KEY (user_id, interest_id),
	CONSTRAINT user_interest_unique UNIQUE(user_id,interest_id),
	CONSTRAINT user_interest_reputation_user_id_fkey FOREIGN KEY (user_id)
		REFERENCES "user" (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT user_interest_reputation_interest_id_fkey FOREIGN KEY (interest_id)
		REFERENCES interest (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);