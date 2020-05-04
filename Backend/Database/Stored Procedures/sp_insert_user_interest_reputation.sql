CREATE OR REPLACE PROCEDURE sp_insert_user_interest_reputation(
	user_id BIGINT,
	interest_id BIGINT,
	reputation BIGINT =0
)
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO user_interest_reputation(user_id, interest_id, reputation)
VALUES (user_id, interest_id, reputation);
COMMIT;
END;
$$;

CALL sp_insert_user_interest_reputation(1,3,0);