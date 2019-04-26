package lib ;

import java.util.Date;
import libinsa.txnscriptUtil ;

public class txnscript {

 // des exemples de fonction attendu dans de ce fichier sont fournis dans
 // http://prodageo.insa-rouen.fr/wiki/pmwiki.php?n=Umlp.EXU9912txnscript
  
  
 /** 
     * 
     * cre'ation dans l'outil de persistence d'un enregistrement de re'servation
	 * suite à la re'ception d'une demande par le controleur.
     *
     * @param email_address    adresse de messagerie de l'associe qui effectue la réservation (ie : solange.smith@gmail.com)
     * @param name  nom de la personne (ie : 23/07/2019)
     * @return id attributé par le SGBD
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
     * @return id attributé par le SGBD
     */

  public int creerEnrReservation ( String id_associe, Date date_arrivee ) 
  {
    return 1 ;
  }
}
