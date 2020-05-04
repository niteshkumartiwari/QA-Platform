CREATE OR REPLACE PROCEDURE sp_insert_interest(
	name VARCHAR,
	is_deleted SMALLINT DEFAULT 0
)
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO interest(name, is_deleted)
VALUES (name, is_deleted);
COMMIT;
END;
$$;

CALL sp_insert_interest(name=>'science');