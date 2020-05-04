CREATE OR REPLACE PROCEDURE sp_insert_question_follower(
	user_id BIGINT ,
	question_id BIGINT
)
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO question_follower(user_id, question_id)
VALUES (user_id, question_id);
COMMIT;
END;
$$;

CALL sp_insert_question_follower(3,1);