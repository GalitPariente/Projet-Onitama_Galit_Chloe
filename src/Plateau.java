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
public class Plateau {

    Case Grille[][] = new Case[5][5];
    int[] useCoordPion; // Ce tableau d'entiers recense les coordonnées d'un pion grâce "TakePion"
    String[] useAttribPion; // Ce tableau de String recense les attributs d'un pion grâce à "TakePion"

    // Créer, à chaque position dans le tableau, une réf objet de classe Case
    public Plateau() {
        
        useCoordPion = new int[2]; // Création du tableau de coordonnées
        useAttribPion = new String[2]; // Création du tableau d'attribut
        
        for (int l = 0; l < 5; l++) {
            for (int c = 0; c < 5; c++) {
                Grille[l][c] = new Case();
            }
        }
    }

    void takePion(Case uneCase) { // Met dans les tableaux les coordonnées et les attributs de la case sélectionnée
        if (uneCase != null && uneCase.PionCourant != null) { // Si la case est occupée par un pion
            useCoordPion[0] = LireCoordColonne(uneCase);
            useCoordPion[1] = LireCoordLigne(uneCase);
            useAttribPion[0] = uneCase.PionCourant.CouleurPion;
            useAttribPion[1] = uneCase.PionCourant.LireRolePion();
        } else if (uneCase != null) { // Si la case n'est pas occupée par un pion
            useCoordPion[0] = LireCoordColonne(uneCase);
            useCoordPion[1] = LireCoordLigne(uneCase);
        } else { // Signal d'erreurs
            useCoordPion[0] = -1;
            useCoordPion[1] = -1;
            useAttribPion[0] = "promblème de couleur";
            useAttribPion[1] = "problème de rôle";
        }
    }
    
    int LireCoordColonne(Case uneCase) {
        for (int l = 0; l < 5; l++) {
            for (int c = 0; c < 5; c++) {
                if (uneCase == Grille[l][c]) {
                    return c; // Renvoie la coordonnée de la colonne de la case sélectionnée
                }
            }
        }
        return -1; // En cas d'erreurs
    }

    // retourne la coordonnée ligne de la case associée
    int LireCoordLigne(Case uneCase) {
        for (int l = 0; l < 5; l++) {
            for (int c = 0; c < 5; c++) {
                if (uneCase == Grille[l][c]) {
                    return l; // Renvoie la coordonnée de la ligne de la case sélectionnée
                }
            }
        }
        return -2; // En cas d'erreurs
    }

    
    // Création des 10 pions (dont 2 rois) sur le plateau
    void PositionnerPionsDepart() { 
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                Pion RoiRouge = new Pion("Rouge");
                RoiRouge.Roi = true;
                Grille[0][2].PionCourant = RoiRouge; // Roi rouge positionné en bas au centre du plateau
                Pion RoiBleu = new Pion("Bleu");
                RoiBleu.Roi = true;
                Grille[4][2].PionCourant = RoiBleu; // Roi bleu poisitionné en haut au centre du plateau
            } else {
                Pion unPionR = new Pion("Rouge");
                Grille[0][i].PionCourant = unPionR; // Pions rouges positionnés sur la ligne du bas
                Pion unPionB = new Pion("Bleu");
                Grille[4][i].PionCourant = unPionB; // Pions bleus positionnés sur la ligne du haut
            }
        }
    }
    
// Initialisation des données à 0 pour toutes les cases
    void ViderPlateau() { 
        for (int l = 0; l < 5; l++) {
            for (int c = 0; c < 5; c++) {
                Grille[l][c].PionCourant = null;
            }
        }
    }

// Initialisation des données à 0 pour les cellules où Grille[l][c].CaseGrise renvoie true 
    void ViderCaseGrise() { 
        for (int l = 0; l < 5; l++) {
            for (int c = 0; c < 5; c++) {
                Grille[l][c].CaseGrise = false;
            }
        }
    }

    boolean CaseOccupee(int l, int c) { 
        if (Grille[l][c].PionCourant != null) {
            System.out.println("case occupée par pion");
            return true; // True ssi la case est occupée par un pion
        }
        return false;
    }

    String LireCouleurPion(int l, int c) { 
        return Grille[l][c].PionCourant.CouleurPion; // Renvoie la couleur du pion courant
    }

    // Suppression du pion du plateau de jeu
    boolean SupprimerPion(int l, int c) { 
        if (Grille[l][c].PionCourant == null) { // Vérif case soit pas vide
            return false;
        } else {
            Grille[l][c].PionCourant = null;
            return true;
        }
    }

    // Première condition gagnante : le roi atteint la position initiale du roi adverse
    boolean ConditionRuisseau(int l, int c) {
        if (Grille[l][c].PionCourant.EtreRoi() == true && Grille[l][c].PionCourant.CouleurPion == "Bleu") {
            if (l == 0 && c == 2) {
                System.out.println("Le joueur bleu gagne!");
                return true;
            } else {
                return false;
            }
        }
        if (Grille[l][c].PionCourant.EtreRoi() == true && Grille[l][c].PionCourant.CouleurPion == "Rouge") {
            if (l == 4 && c == 2) {
                System.out.println("Le joueur rouge gagne!");
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    
    // Affichage sur la console
    void AfficherPlateauSurConsole() {
        for (int l = 4; l >= 0; l--) { // Cette boucle est décrémentée car l'affichage est inversé
            for (int c = 0; c < 5; c++) {
                if (Grille[l][c].PionCourant == null && Grille[l][c].CaseGrise == true) {
                    System.out.print(" G "); // Case grise
                } else if (Grille[l][c].PionCourant == null){
                    System.out.print(" V "); // Case vide
                } else if (Grille[l][c].CaseGrise == true) {
                    System.out.print(" G "); //Case grise
                } else if ((Grille[l][c].PionCourant.CouleurPion) != "Rouge" && Grille[l][c].PionCourant.Roi == true) {
                    System.out.print(" RR "); // Roi Rouge
                } else if ((Grille[l][c].PionCourant.CouleurPion) != "Rouge") {
                    System.out.print(" PR "); //Pion Rouge
                } else if ((Grille[l][c].PionCourant.CouleurPion) != "Bleu" && Grille[l][c].PionCourant.Roi == true) {
                    System.out.print(" RB "); // Roi Bleu
                } else if ((Grille[l][c].PionCourant.CouleurPion) != "Bleu") {
                    System.out.print(" PB "); //Pion Bleu
                }
            }
            System.out.println(" " + (l + 1)); // Affichage du numéros de lignes (l+1) 
            // On utilise (l+1) car le tableau commence à 0
        }

        for (int c = 0; c < 5; c++) {
            System.out.print(" " + (c + 1) + " "); // Affichage des colonnes (c+1)
        }
        System.out.println(); // Affichage global du tableau
    }

    void PlacerCaseGrise(Joueur unJoueur, int l, int c, Carte uneCarte) {
        if (unJoueur.CouleurJoueur == "Rouge") {
            PlacerCaseGriseRouge(l, c, uneCarte);
        } else {
            PlacerCaseGriseBleu(l, c, uneCarte);
        }
    }

    void PlacerCaseGriseRouge(int l, int c, Carte uneCarte) { 
        System.out.println("case rouge");
        String couleur_carte = uneCarte.LireCarte();
        switch (couleur_carte) {
            
            case "Anguille":
                if ((l >= 0 && l < 4) && (c >= 1 && c < 5)) {
                    Grille[l + 1][c - 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l - 1][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l][c + 1].CaseGrise = true;
                }
                break;
                
            case "Boeuf":
                if ((l >= 0 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l][c + 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 5)) {
                    Grille[l - 1][c].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 5)) {
                    Grille[l + 1][c].CaseGrise = true;
                }
                break;
                
            case "Cheval":
                if ((l >= 0 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 5)) {
                    Grille[l + 1][c].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 5)) {
                    Grille[l - 1][c].CaseGrise = true;
                }
                break;
                
            case "Cobra":
                if ((l >= 0 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 4)) {
                    Grille[l + 1][c + 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l - 1][c + 1].CaseGrise = true;
                }
                break;
                
            case "Coq":
                if ((l >= 0 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l][c + 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l - 1][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 4)) {
                    Grille[l + 1][c + 1].CaseGrise = true;
                }
                //System.out.println("carte Coq");
                break;

            case "Crabe":
                if ((l >= 0 && l < 5) && (c >= 2 && c < 5)) {
                    Grille[l][c - 2].CaseGrise = true;
                }
                if ((l >= 0 && l < 5) && (c >= 0 && c < 3)) { 
                    Grille[l][c + 2].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 5)) {
                    Grille[l + 1][c].CaseGrise = true;
                }
                System.out.println("carte Crabe");
                break;

            case "Dragon":
                if ((l >= 1 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l - 1][c - 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l - 1][c + 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 2 && c < 5)) { 
                    Grille[l + 1][c - 2].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 3)) {
                    Grille[l + 1][c + 2].CaseGrise = true;
                }
                break;
                
            case "Elephant":
                if ((l >= 0 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l][c + 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 1 && c < 5)) {
                    Grille[l + 1][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 4)) {
                    Grille[l + 1][c + 1].CaseGrise = true;
                }
                break;
                
            case "Grenouille":
                if ((l >= 0 && l < 5) && (c >= 2 && c < 5)) {
                    Grille[l][c - 2].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 1 && c < 5)) {
                    Grille[l + 1][c - 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l - 1][c + 1].CaseGrise = true;
                }
                break;
                
            case "Lapin":
                if ((l >= 1 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l - 1][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 4)) {
                    Grille[l + 1][c + 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 5) && (c >= 0 && c < 3)) { 
                    Grille[l][c + 2].CaseGrise = true;
                }
                break;
                
            case "Mante":
                if ((l >= 0 && l < 4) && (c >= 1 && c < 5)) { // Coordonnée possibles restreintes
                    Grille[l + 1][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 4)) {
                    Grille[l + 1][c + 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 5)) {
                    Grille[l - 1][c].CaseGrise = true;
                }
                //System.out.println("carte Mante"); //Vérif sur la console 
                break;
                
            case "Oie":
                if ((l >= 0 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l][c + 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 1 && c < 5)) {
                    Grille[l + 1][c - 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l - 1][c + 1].CaseGrise = true;
                }
                break;
                
            case "Sanglier":
                if ((l >= 0 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l][c + 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 5)) {
                    Grille[l + 1][c].CaseGrise = true;
                }
                break;

            case "Singe":
                if ((l >= 0 && l < 4) && (c >= 1 && c < 5)) {
                    Grille[l + 1][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 4)) {
                    Grille[l + 1][c + 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l - 1][c - 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l - 1][c + 1].CaseGrise = true;
                }
                break;

            case "Tigre":
                if ((l >= 0 && l < 3) && (c >= 0 && c < 5)) { 
                    Grille[l + 2][c].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 5)) {
                    Grille[l - 1][c].CaseGrise = true;
                }
                break;

            default: // Pour la case "Grue"
                if ((l >= 1 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l - 1][c - 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l - 1][c + 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 5)) {
                    Grille[l + 1][c].CaseGrise = true;
                }
                break;
        }
    }

    // Visualisation des potentiels mouvements
    void PlacerCaseGriseBleu(int l, int c, Carte uneCarte) { 
        System.out.println("case bleu");
        String couleur_carte = uneCarte.LireCarte();
        switch (couleur_carte) {

            case "Anguille":
                if ((l >= 1 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l - 1][c + 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 4)) {
                    Grille[l + 1][c + 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l][c - 1].CaseGrise = true;
                }
                break;
                
            case "Boeuf":
                if ((l >= 0 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 5)) {
                    Grille[l + 1][c].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 5)) {
                    Grille[l - 1][c].CaseGrise = true;
                }
                break;
                
            case "Cheval":
                if ((l >= 0 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l][c + 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 5)) {
                    Grille[l + 1][c].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 5)) {
                    Grille[l - 1][c].CaseGrise = true;
                }
                break;
                
            case "Cobra":
                if ((l >= 0 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l][c + 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 1 && c < 5)) {
                    Grille[l + 1][c - 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l - 1][c - 1].CaseGrise = true;
                }
                break;
                
            case "Coq":
                if ((l >= 0 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l][c + 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l - 1][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 1 && c < 4)) {
                    Grille[l + 1][c + 1].CaseGrise = true;
                }
                System.out.println("carte Coq");
                break;

            case "Crabe":
                if ((l >= 0 && l < 5) && (c >= 2 && c < 5)) { 
                    Grille[l][c - 2].CaseGrise = true;
                }
                if ((l >= 0 && l < 5) && (c >= 0 && c < 3)) { 
                    Grille[l][c + 2].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 5)) {
                    Grille[l - 1][c].CaseGrise = true;
                }
                System.out.println("carte Crabe");
                break;
                
            case "Dragon":
                if ((l >= 0 && l < 4) && (c >= 1 && c < 5)) {
                    Grille[l + 1][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 4)) {
                    Grille[l + 1][c + 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 2 && c < 5)) {
                    Grille[l - 1][c - 2].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 3)) {
                    Grille[l - 1][c + 2].CaseGrise = true;
                }
                break;
         
            case "Elephant":
                if ((l >= 0 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l][c + 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l - 1][c + 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l - 1][c - 1].CaseGrise = true;
                }
                break;
                
            case "Grenouille":
                if ((l >= 0 && l < 5) && (c >= 0 && c < 3)) { 
                    Grille[l][c + 2].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 1 && c < 5)) {
                    Grille[l + 1][c - 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l - 1][c + 1].CaseGrise = true;
                }
                break;
             
            case "Lapin":
                if ((l >= 1 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l - 1][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 4)) {
                    Grille[l + 1][c + 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 5) && (c >= 2 && c < 5)) { 
                    Grille[l][c - 2].CaseGrise = true;
                }
                break;
                
            case "Mante":
                if ((l >= 1 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l - 1][c + 1].CaseGrise = true;
                    //System.out.println("carte Mante");
                }
                if ((l >= 1 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l - 1][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 5)) {
                    Grille[l + 1][c].CaseGrise = true;
                }
                System.out.println("carte Mante");
                break;
                
            case "Oie":
                if ((l >= 0 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l][c + 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 1 && c < 5)) {
                    Grille[l + 1][c - 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l - 1][c + 1].CaseGrise = true;
                }
                break;
                
            case "Sanglier":
                if ((l >= 0 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l][c + 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 5)) {
                    Grille[l - 1][c].CaseGrise = true;
                }
                break;

            case "Singe":
                if ((l >= 0 && l < 4) && (c >= 1 && c < 5)) {
                    Grille[l + 1][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 4)) {
                    Grille[l + 1][c + 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 1 && c < 5)) {
                    Grille[l - 1][c - 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 4)) {
                    Grille[l - 1][c + 1].CaseGrise = true;
                }
                break;

            case "Tigre":
                if ((l >= 2 && l < 5) && (c >= 0 && c < 5)) { 
                    Grille[l - 2][c].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 5)) {
                    Grille[l + 1][c].CaseGrise = true;
                }
                break;

            default: //Pour la case "Grue"
                if ((l >= 0 && l < 4) && (c >= 1 && c < 5)) {
                    Grille[l + 1][c - 1].CaseGrise = true;
                }
                if ((l >= 0 && l < 4) && (c >= 0 && c < 4)) {
                    Grille[l + 1][c + 1].CaseGrise = true;
                }
                if ((l >= 1 && l < 5) && (c >= 0 && c < 5)) {
                    Grille[l - 1][c].CaseGrise = true;
                }
                break;

        }

    }

    boolean PeutDeplacerPion(Joueur unJoueur, int l, int c) {
        if (Grille[l][c].PionCourant == null && Grille[l][c].CaseGrise == false) { // Si la case sélectionée n'est pas grise 
            System.out.println("Vous ne pouvez pas vous déplcer ici");
            return false;
        } else if (Grille[l][c].PionCourant == null && Grille[l][c].CaseGrise != false) { //Si la case sélectionée est vide et grise
            System.out.println("Votre pion peut être déplacé");
            return true; // Ture ssi le pion peut être déplacé
        } else if (Grille[l][c].CaseGrise != false && Grille[l][c].PionCourant.CouleurPion == unJoueur.CouleurJoueur) { //La case sélectionnée est grise et est occupée par un pion de la couleur qui joue (qui sélectionne la case)
            System.out.println("La case sélectionnée est déjà occupée par l'un de vos pions");
            return false;
        } else { //(Grille[l][c].CaseGrise != false && Grille[l][c].PionCourant.CouleurPion != unJoueur.CouleurJoueur) // La case sélectionnée est grise et est occupée par un pion de la couleur du joueur adverse à celui qui joue (qui sélectionne la case)
            System.out.println("Votre pion peut être déplacé, la case sélectionnée est occupée par un pion adverse");
            return true;
        }
    }

    int LireCoordL(Case c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    int LireCoordC(Case c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}