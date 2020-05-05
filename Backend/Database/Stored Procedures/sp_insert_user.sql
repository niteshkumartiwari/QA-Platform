CREATE OR REPLACE PROCEDURE sp_insert_user(
	username VARCHAR  ,
	first_name VARCHAR ,
	last_name VARCHAR ,
	email VARCHAR ,
	password VARCHAR,
	bio VARCHAR =null,
	about_me VARCHAR =null,
	profile_pic VARCHAR =null,
	reputations BIGINT =null,
	is_deleted SMALLINT =null,
	is_banned SMALLINT =null,
	last_login TIMESTAMP DEFAULT (Now() AT TIME ZONE 'utc')
)
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO "user" (username,first_name,last_name,email,password,bio,about_me,profile_pic,reputations,is_deleted, is_banned,last_login)
VALUES (username,first_name,last_name,email,password,bio,about_me,profile_pic,reputations,is_deleted, is_banned,last_login);
COMMIT;
END;
$$;

--CALL sp_insert_user(username=>'happy',first_name=>'anuj',last_name=>'gupta',
				   email=>'abc@gmail.com',reputations=>25);