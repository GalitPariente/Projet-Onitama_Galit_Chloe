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
public class Carte {

    String NomCarte;
    int Matrice[][];
    Carte CarteCourante;
    
    public Carte(String unNom){ // Ce constructeur prend en paramètre le nom de la Carte
        NomCarte = unNom;
    }

    // Constructeur inutilisé, à prendre en compte ssi on prend en compte les matrices de déplacement
    public Carte(String unNom, String uneCouleur, int MatCoord[][]){ 
        NomCarte = unNom;
        Matrice = new int[5][5];
        
        for (int i=0; i<MatCoord.length; i++){
        int x = MatCoord[i][0];
        int y = MatCoord[i][1];
        Matrice[x][y] = 1;
    }
        
}  

String LireCarte(){
   return NomCarte; //Retourne le nom de la carte au joueur
}      
}
