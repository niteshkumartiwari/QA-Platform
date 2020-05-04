CREATE OR REPLACE PROCEDURE sp_insert_comment_answer(
	content VARCHAR,
	answer_id BIGINT,
	replied_by BIGINT,
	upkudo BIGINT =0
)
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO comment_answer(content, answer_id,replied_by,upkudo)
VALUES (content, answer_id,replied_by,upkudo);
COMMIT;
END;
$$;

CALL sp_insert_comment_answer('nice-video',3,3,1);