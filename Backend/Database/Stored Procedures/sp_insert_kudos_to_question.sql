CREATE OR REPLACE PROCEDURE sp_insert_kudos_to_question(
	question_id BIGINT,
	user_id BIGINT
)
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO question_user_kudo(question_id, user_id) VALUES (question_id,user_id);
COMMIT;
END;
$$;

--CALL sp_insert_kudos_to_question(1,3)