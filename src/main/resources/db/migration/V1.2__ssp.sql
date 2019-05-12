-- ce fichier doit contenir autant de traitements que de traitements déclarée dans le fichier txnscript.java de votre projet
-- voir http://prodageo.insa-rouen.fr/wiki/pmwiki.php?n=Filrouge.CoderPartieBd

DROP FUNCTION IF EXISTS getLabel ;
CREATE FUNCTION getLabel () 
 RETURNS TABLE (
 query_id INTEGER,
 query_label VARCHAR(100) 
) 
AS $$
BEGIN
 RETURN QUERY SELECT
 id, label10
 FROM
 TEST10 ;
END; $$ 
LANGUAGE 'plpgsql';
