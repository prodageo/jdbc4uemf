package lib ;

import java.util.Date;
import libinsa.txnscriptUtil ;
import lib.txnscript ;

public class txnscenario {

 /** 
     * 
     * La fonction test doit réutiliser l'ensemble des opérations de la classe
	 * dans un enchaînement et avec des valeurs de paramètres qui permettent 
     * de réaliser un scénario réaliste. Par exemple :
	 *   - créer un enregistrement
	 *   - vérifier qu'une recherche renvoie bien les valeurs communiquées à la création
	 *   - etc ...
	 * 
     * @return cette chaîne simule un écran 
	 *         dans le but de stocker
	 *         l'ensemble des chaines que l'on souhaiterait afficher
     */
 
  public String test()
  {
	String log = "" ;

	// These 3 lines are only for running the examples
	// If you are confident, you can comment it.
	String the_message = "Run the example" + "\n" ; 		
	txnscriptUtil txnUtil = new txnscriptUtil() ;
	log = txnUtil.test () ;		

	// ******************
	// For INSA-ROUEN : put your the code here to call any operation defined in this class
	the_message = "Run the application " + "\n" ; 		
	txnscript txn = new txnscript() ;
	
	int i = txn.creerAssocie("mickey@disney.com", "MICKEY") ;
	
	
	// ******************
	return log ;
  }
}
