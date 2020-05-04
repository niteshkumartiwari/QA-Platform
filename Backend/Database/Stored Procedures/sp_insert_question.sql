CREATE OR REPLACE PROCEDURE sp_insert_question
    (title VARCHAR, 
    description VARCHAR, 
    asked_by BIGINT,
    edited_by BIGINT=0, 
    is_image SMALLINT = 0,
    res_link VARCHAR=null,
    upkudo BIGINT = 0,
    downkudo BIGINT = 0,
    is_deleted SMALLINT = 0,
    is_banned SMALLINT = 0,
    last_updated TIMESTAMP=(Now() AT TIME ZONE 'utc'),
    thumbnail VARCHAR=null) 
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO question(title, description,asked_by, edited_by,is_image,res_link,
    upkudo,downkudo,is_deleted,is_banned,last_updated,thumbnail) VALUES (title, description,asked_by, edited_by,is_image,res_link,upkudo,downkudo,is_deleted,is_banned,last_updated,thumbnail);
COMMIT;
END;
$$;
