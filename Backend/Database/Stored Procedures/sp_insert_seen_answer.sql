CREATE OR REPLACE PROCEDURE sp_insert_seen_answer(
	user_id BIGINT,
	answer_id BIGINT
)
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO seen_answer(user_id,answer_id)
VALUES (user_id,answer_id);
COMMIT;
END;
$$;

--CALL sp_insert_seen_answer(1,1);