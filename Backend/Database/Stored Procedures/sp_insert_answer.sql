CREATE OR REPLACE PROCEDURE sp_insert_answer
				(question_id BIGINT, 
				answered_by BIGINT,
				res_link VARCHAR,
				is_image SMALLINT=0, 
				thumbnail VARCHAR= null, 
				caption VARCHAR= null,
				"views" BIGINT=0,
				upkudo BIGINT=0, 
				downkudo BIGINT=0,
				is_deleted SMALLINT=0, 
				is_shattered SMALLINT=0, 
				last_updated TIMESTAMP=(Now() AT TIME ZONE 'utc'))
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO answer(is_image ,res_link ,thumbnail, caption, "views", answered_by ,question_id , upkudo , downkudo ,is_deleted , is_shattered , last_updated) VALUES (is_image ,res_link ,thumbnail, caption, "views", answered_by ,question_id , upkudo , downkudo ,is_deleted , is_shattered , last_updated);
COMMIT;
END;
$$;