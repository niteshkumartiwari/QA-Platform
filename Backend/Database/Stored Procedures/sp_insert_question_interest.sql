CREATE OR REPLACE PROCEDURE sp_insert_question_interest(
	question_id BIGINT,
	interest_id BIGINT
)
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO question_interest(question_id,interest_id)
VALUES (question_id,interest_id);
COMMIT;
END;
$$;

CALL sp_insert_question_interest(1,1);