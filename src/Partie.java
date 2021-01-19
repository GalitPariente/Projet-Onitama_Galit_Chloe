/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiniProjet_ONITAMA_PZ;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author zuffe
 */
// Contient les méthodes en lien avec la console et celles non utilisées dans la fenêtre de jeu
public class Partie {

    Joueur ListeJoueurs[] = new Joueur[2];  // liste de 2 joueurs à 2 cases, sera initialisée quand les joueurs seront créés
    Plateau PlateauJeu = new Plateau(); // Création de la réf objet GrilleJeu
    Joueur JoueurCourant;
    Carte CarteTransition;
    Carte CarteCourante;
    Carte TabCartePartie[] = new Carte[5]; // Tableau de 5 objets Carte (de déplacements) pour une partie

   // Matrice pas utilisées (cf constructeur carte)
    //int[][] Anguille = {{1, 1}, {3, 1}, {2, 3}};
    Carte CarteAnguille = new Carte("Anguille");
    //int[][] Boeuf = {{1, 2}, {3, 2}, {2, 3}};
    Carte CarteBoeuf = new Carte("Boeuf");
    //int[][] Cheval = {{2, 1}, {1, 2}, {3, 2}};
    Carte CarteCheval = new Carte("Cheval");
    //int[][] Coq = {{1, 1}, {2, 1}, {2, 3}, {3, 3}};
    Carte CarteCoq = new Carte("Coq");
    //int[][] Cobra = {{2, 1}, {1, 3}, {3, 3}};
    Carte CarteCobra = new Carte("Cobra");
    //int[][] Crabe = {{2, 0}, {3, 2}, {2, 2}};
    Carte CarteCrabe = new Carte("Crabe");
    //int[][] Dragon = {{3, 0}, {1, 1}, {1, 3}, {3, 4}};
    Carte CarteDragon = new Carte("Dragon");
    //int[][] Elephant = {{2, 1}, {3, 1}, {2, 3}, {3, 3}};
    Carte CarteElephant = new Carte("Elephant");
    //int[][] Grenouille = {{2, 0}, {1, 3}, {3, 1}};
    Carte CarteGrenouille = new Carte("Grenouille");
    //int[][] Grue = {{1, 1}, {3, 2}, {1, 3}};
    Carte CarteGrue = new Carte("Grue");
    //int[][] Lapin = {{1, 1}, {3, 3}, {2, 4}};
    Carte CarteLapin = new Carte("Lapin");
    //int[][] Mante = {{1, 2}, {3, 1}, {3, 3}};
    Carte CarteMante = new Carte("Mante");
    //int[][] Oie = {{1, 2}, {3, 1}, {2, 1}, {2, 3}};
    Carte CarteOie = new Carte("Oie");
    //int[][] Sanglier = {{2, 1}, {2, 3}, {3, 2}};
    Carte CarteSanglier = new Carte("Sanglier");
    //int[][] Singe = {{1, 1}, {1, 3}, {3, 1}, {3, 3}};
    Carte CarteSinge = new Carte("Singe");
    //int[][] Tigre = {{1, 2}, {4, 2}};
    Carte CarteTigre = new Carte("Tigre");

    //Regroupement des 16 cartes dans une Arrayliste
    ArrayList<Carte> ListeCartes = new ArrayList<Carte>() { 
        {
            add(CarteAnguille);
            add(CarteBoeuf);
            add(CarteCheval);
            add(CarteCobra);
            add(CarteCoq);
            add(CarteCrabe);
            add(CarteDragon);
            add(CarteElephant);
            add(CarteGrenouille);
            add(CarteGrue);
            add(CarteLapin);
            add(CarteMante);
            add(CarteOie);
            add(CarteSanglier);
            add(CarteSinge);
            add(CarteTigre);
        }
    };

    
    // Initialisation sur la console
    void initialiserPartie() {
        
        // Vide le plateau de jeu
        PlateauJeu.ViderPlateau();

        // Création des 2 joueurs
        // Affectation dans le tableau
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez le nom du Joueur 1 : ");
        Joueur Joueur1 = new Joueur(sc.nextLine()); // Création des objets joueur
        System.out.println("Entrez le nom du Joueur 2 : ");
        Joueur Joueur2 = new Joueur(sc.nextLine());

        // Affectation des réf au tableau
        ListeJoueurs[0] = Joueur1;
        ListeJoueurs[1] = Joueur2;

        // Attribution des couleurs
        AttribuerCouleursAuxJoueurs();

        // Placement des pions
        PlateauJeu.PositionnerPionsDepart();

        // Tirage au sort des 5 cartes pour la partie
        DefinirCartesPartie();

        // Attribution des cartes
        Joueur1.CarteEnMain[0] = TabCartePartie[0];
        Joueur1.CarteEnMain[1] = TabCartePartie[1];
        Joueur2.CarteEnMain[0] = TabCartePartie[2];
        Joueur2.CarteEnMain[1] = TabCartePartie[3];
        
        // Affectation de la dernière en tant que carte de transition
        CarteTransition = TabCartePartie[4];

        // Détermination du joueur qui va commencer
        Random joueur = new Random();
        boolean premier_joueur = joueur.nextBoolean();
        if (premier_joueur) {
            JoueurCourant = ListeJoueurs[0];
        } else {
            JoueurCourant = ListeJoueurs[1];
        }

        // Affichage sur la console
        System.out.println(Joueur1.NomJoueur + " a les cartes " + Joueur1.CarteEnMain[0].NomCarte + " et " + Joueur1.CarteEnMain[1].NomCarte);
        System.out.println(Joueur2.NomJoueur + " a les cartes " + Joueur2.CarteEnMain[0].NomCarte + " et " + Joueur2.CarteEnMain[1].NomCarte);
        System.out.println("La carte transition est " + CarteTransition.NomCarte);

        System.out.println(Joueur1.NomJoueur + " a les pions de couleur " + Joueur1.CouleurJoueur);
        System.out.println(Joueur2.NomJoueur + " a les pions de couleur " + Joueur2.CouleurJoueur);

        // Affichage du jeu initialisé sur la console
        PlateauJeu.AfficherPlateauSurConsole();
    }

    //Tirage au sort de 5 cartes parmis 16
    void DefinirCartesPartie() { 
        Random rand = new Random();
        int NbCartes = 5;

        for (int i = 0; i < NbCartes; i++) {
            int randomIndex = rand.nextInt(ListeCartes.size()); // Choix d'un chiffre de 0 à 15 (ça 16 cartes au total)
            Carte randomElement = ListeCartes.get(randomIndex);
            TabCartePartie[i] = randomElement; // Affectation de cette carte au tableau
            //System.out.println(TabCartePartie[i].NomCarte);
            ListeCartes.remove(randomIndex); // Retire le n° au paquet pour ne pas choisir 2 fois la même carte
        }
    }

    Carte CarteChoisie(String unNomCarte) {
        if (JoueurCourant.CarteEnMain[0].NomCarte == unNomCarte) {
            return JoueurCourant.CarteEnMain[0];
        } else {
            return JoueurCourant.CarteEnMain[1]; // Renvoie la carte dans la main du joueur
        }
    }

    // Change la carte jouée par la carte de transition
    Carte EchangeCarte() {
        Carte NouvCarteTransit;
        for (int i = 0; i < 5; i++) {
            if (TabCartePartie[i] == CarteCourante) {
                NouvCarteTransit = TabCartePartie[i];
                return NouvCarteTransit;
            }
        }
        CarteCourante = CarteTransition;
        
        return null;
    }

    private void AttribuerCouleursAuxJoueurs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}