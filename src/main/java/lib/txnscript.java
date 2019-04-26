package lib ;

import java.util.Date;
import libinsa.txnscriptUtil ;

public class txnscript {

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
	txnscriptUtil txnUtil = new txnscriptUtil() ;
	
	log = txnscriptUtil.initConnection () ;
	
	return log ;
  }

 // des exemples de fonction attendu dans de ce fichier sont fournis dans
 // http://prodageo.insa-rouen.fr/wiki/pmwiki.php?n=Umlp.EXU9912txnscript
  
  
 /** 
     * 
     * cre'ation dans l'outil de persistence d'un enregistrement de re'servation
	 * suite à la re'ception d'une demande par le controleur.
     *
     * @param email_address    adresse de messagerie de l'associe qui effectue la réservation (ie : solange.smith@gmail.com)
     * @param name  nom de la personne (ie : 23/07/2019)
     * @return id attributé par la base
     */

  public int creerAssocie ( String email_address, String name ) 
  {
    return 1 ;
  }
	
     /** 
     * 
     * cre'ation dans l'outil de persistence d'un enregistrement de re'servation
	 * suite à la re'ception d'une demande par le controleur.
     *
     * @param id_associe    adresse de messagerie de l'associe qui effectue la réservation (ie : solange.smith@gmail.com)
     * @param date_arrivee  date d'arrivée des amis de l'associe' dans la maison (ie : 23/07/2019)
     * @return              true si la reque'te a re'ussi
     */

  public boolean creerEnrReservation ( String id_associe, Date date_arrivee ) 
  {
    return true ;
  }
}
