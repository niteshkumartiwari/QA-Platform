CREATE OR REPLACE PROCEDURE sp_insert_seen_question(
	user_id BIGINT,
	question_id BIGINT
)
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO seen_question(user_id,question_id)
VALUES (user_id,question_id);
COMMIT;
END;
$$;

--CALL sp_insert_seen_question(1,1);