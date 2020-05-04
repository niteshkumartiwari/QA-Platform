CREATE OR REPLACE PROCEDURE sp_insert_kudos_to_answer(
	answer_id BIGINT,
	user_id BIGINT
)
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO answer_user_kudo(answer_id, user_id) VALUES (answer_id,user_id);
COMMIT;
END;
$$;

CALL sp_insert_kudos_to_answer(1,3)