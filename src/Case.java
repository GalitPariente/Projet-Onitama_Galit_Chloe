/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiniProjet_ONITAMA_PZ;

/**
 *
 * @author zuffe
 */
public class Case {
    
    Pion PionCourant;
    boolean CaseGrise;
    boolean EtreRoi;
    
public Case () { //Initialisation des attributs
    PionCourant = null;
    CaseGrise = false;
    EtreRoi = false;
} 

boolean AffecterPion(Pion unPion){ //Affecte le pion à une case après que celui-ci ai subit un mouvement
    if (PionCourant == null){
       PionCourant = unPion;
       PionCourant.Roi = unPion.Roi;
       PionCourant.CouleurPion = unPion.CouleurPion;
       //System.out.println(PionCourant.Roi);
       //System.out.println(PionCourant.CouleurPion); // Simple vérif
        return true;
    }
    else{
      return false;
    }
}

boolean PresenceCaseVide(){
    if (PionCourant == null){
        return true; //True si case vide
    }
    else{
        return false;
    }
}

void AffecterCaseGrise(){
    CaseGrise = true; //Grise la case concernée
}

String LireCouleurDuPion(){
    if (PionCourant == null){
        return "vide";
    }
    else{
        return PionCourant.CouleurPion; //Renvoie la couleur du pion qui joue
    }
}

boolean LireRoleDuPion(){
    if (PionCourant == null){
        return false;
    }
    else if (PionCourant.Roi != true) {
        return false;
    }
    else {
        return true; //Renvoie la couleur du pion joué
    }
}

boolean presenceRoi(){
    if (LireRoleDuPion() == true){
        EtreRoi = true ;
        return true; //True s'il y a un roi
    }
    else {
        return false;
    }
}

boolean presenceCaseGrise(){
    if (CaseGrise == true){
        return true; //True s'il y a una case grise
    }
    else {
        return false;
    }
}

boolean SupprimerPion(){
    if(PionCourant==null){
        return false;
    }
    else{
        PionCourant=null;
        return true; //Supprime le pion
    }
}

}
