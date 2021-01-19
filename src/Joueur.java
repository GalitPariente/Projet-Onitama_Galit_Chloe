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
public class Joueur {

    String NomJoueur;
    String CouleurJoueur;
    Carte CarteEnMain[] = new Carte[2]; // Représente le tableau de cartes en main de tel ou tel joueur lors de la partie
    Carte CarteCourante;

    public Joueur(String unNomJoueur) {
        NomJoueur = unNomJoueur;
    }

    void AffecterCouleur(String uneCouleurJoueur) { // Affectation de la couleur en paramètre au joueur
        CouleurJoueur = uneCouleurJoueur;
    }

    String CouleurDuJoueur() { // Permet de récupérer la couleur dans la classe Grille (cf classe Grille)
        return CouleurJoueur;
    }
    
    boolean ChoisirCarteDansMain(Carte uneCarte) {
        if (uneCarte.NomCarte == CarteEnMain[0].NomCarte || uneCarte.NomCarte == CarteEnMain[1].NomCarte) {
            return true; // True ssi la carte sélectionnée par le joueur est bien présnete dans sa main
        } else {
            System.out.println("Erreur, la carte sélectionnée n'est pas dans votre jeu");
            return false;
        }
    }

    String NomCarteChoisieDansMain(Carte uneCarte) {
        if (ChoisirCarteDansMain(uneCarte) != true) {
            return "Erreur, carte sélectionnée non valide";
        }
        return uneCarte.NomCarte; // Renvoie le nom de la carte choisie
    }
}