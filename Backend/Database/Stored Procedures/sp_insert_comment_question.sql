CREATE OR REPLACE PROCEDURE sp_insert_comment_question(
	content VARCHAR,
	question_id BIGINT,
	replied_by BIGINT,
	upkudo BIGINT =0
)
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO comment_question(content, question_id,replied_by,upkudo)
VALUES (content, question_id,replied_by,upkudo);
COMMIT;
END;
$$;

--CALL sp_insert_comment_question('nice-video',3,3,1);