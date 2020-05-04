CREATE OR REPLACE PROCEDURE sp_insert_follower(
	follower_id BIGINT,
	followee_id BIGINT
)
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO people_follower(follower_id, followee_id)
VALUES (follower_id, followee_id);
COMMIT;
END;
$$;

--CALL sp_insert_interest(name=>'technology');