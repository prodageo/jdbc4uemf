DROP FUNCTION IF EXISTS getMissionnaires2 ;
CREATE FUNCTION getMissionnaires2 () 
 RETURNS TABLE (
 query_nom VARCHAR,
 query_prenom VARCHAR
) 
AS $$
BEGIN
 RETURN QUERY SELECT
 nom,
 prenom
 FROM
 missionnaires ;
END; $$ 
LANGUAGE 'plpgsql';
