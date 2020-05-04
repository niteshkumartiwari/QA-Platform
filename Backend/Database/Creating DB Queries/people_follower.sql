DROP TABLE IF EXISTS people_follower;

CREATE TABLE IF NOT EXISTS people_follower (
	follower_id BIGINT NOT NULL,
	followee_id BIGINT NOT NULL,
	PRIMARY KEY (follower_id, followee_id),
	CONSTRAINT people_follower_follower_id_fkey FOREIGN KEY (follower_id)
		REFERENCES "user" (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT people_follower_followee_id_fkey FOREIGN KEY (followee_id)
		REFERENCES "user" (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);