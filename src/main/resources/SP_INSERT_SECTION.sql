DROP PROCEDURE SP_INSERT_SECTION;

DELIMITER $$

CREATE PROCEDURE SP_INSERT_SECTION
    ( IN id INT, IN section_name VARCHAR(50), IN delegate_id INT, OUT rslt INT )
BEGIN
    INSERT INTO section values (id, section_name, delegate_id);
    SET rslt = 456;
END$$

DELIMITER ;