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
public class Pion {

    String CouleurPion;
    boolean Roi; 

    // Initialisation des attributs
    public Pion(String uneCouleurPion) {
        CouleurPion = uneCouleurPion;
        Roi = false;
    }

    // Création d'un second constructeur pour les cases non occupées par des pions
    public Pion() {
        CouleurPion = null;
        Roi = false;
    }

    String LireCouleurPion() {
        return CouleurPion; // Renvoie la couleur du pion
    }

    // Renvoie le rôle du pion en String
    String LireRolePion() {
        if (Roi == true) {
            return "Roi";
        } else {
            return "Pion";
        }
    }

    boolean EtreRoi() { 
        if (Roi == true) { 
            return true; // ssi le pion est roi
        } else {
            return false;
        }
    }
}
