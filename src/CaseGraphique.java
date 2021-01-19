/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiniProjet_ONITAMA_PZ;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author zuffe
 */
public class CaseGraphique extends JButton { //Partie graphique

    Case CaseAssociee;

    // On crée les images qui seront utilisées dans la partie graphique
    ImageIcon img_Case_Grise = new javax.swing.ImageIcon(getClass().getResource("/images/CaseGrise.png"));
    ImageIcon img_Pion_Bleu = new javax.swing.ImageIcon(getClass().getResource("/images/CasePionBleu.png"));
    ImageIcon img_Pion_Bleu_Grise = new javax.swing.ImageIcon(getClass().getResource("/images/CasePionBleuGrise.png"));
    ImageIcon img_Pion_Rouge = new javax.swing.ImageIcon(getClass().getResource("/images/CasePionRouge.png"));
    ImageIcon img_Pion_Rouge_Grise = new javax.swing.ImageIcon(getClass().getResource("/images/CasePionRougeGrise.png"));
    ImageIcon img_Roi_Bleu = new javax.swing.ImageIcon(getClass().getResource("/images/CaseRoiBleu.png"));
    ImageIcon img_Roi_Bleu_Grise = new javax.swing.ImageIcon(getClass().getResource("/images/CaseRoiBleuGrise.png"));
    ImageIcon img_Roi_Rouge = new javax.swing.ImageIcon(getClass().getResource("/images/CaseRoiRouge.png"));
    ImageIcon img_Roi_Rouge_Grise = new javax.swing.ImageIcon(getClass().getResource("/images/CaseRoiRougeGrise.png"));
    ImageIcon img_Case_Vide = new javax.swing.ImageIcon(getClass().getResource("/images/CaseVide.png"));

    public CaseGraphique(Case uneCase) { //Initialisation des attributs
        CaseAssociee = uneCase;
    }

    @Override
    public void paintComponent(Graphics G) { //Affichage des images
        super.paintComponent(G);
        if (CaseAssociee.presenceCaseGrise() == true && CaseAssociee.PresenceCaseVide() == true) {
            setIcon(img_Case_Grise);
        } else if (CaseAssociee.LireCouleurDuPion() == "Bleu" && CaseAssociee.presenceCaseGrise() == true) {
            setIcon(img_Pion_Bleu_Grise);
        } else if (CaseAssociee.LireCouleurDuPion() == "Rouge" && CaseAssociee.presenceCaseGrise() == true) {
            setIcon(img_Pion_Rouge_Grise);
        } else if (CaseAssociee.presenceRoi() == true && CaseAssociee.LireCouleurDuPion() == "Bleu") {
            setIcon(img_Roi_Bleu);
        } else if (CaseAssociee.presenceCaseGrise() == true && CaseAssociee.presenceRoi() == true && CaseAssociee.LireCouleurDuPion() == "Bleu") {
            setIcon(img_Roi_Bleu_Grise);
        } else if (CaseAssociee.presenceRoi() == true && CaseAssociee.LireCouleurDuPion() == "Rouge") {
            setIcon(img_Roi_Rouge);
        } else if (CaseAssociee.presenceCaseGrise() == true && CaseAssociee.presenceRoi() == true && CaseAssociee.LireCouleurDuPion() == "Rouge") {
            setIcon(img_Roi_Rouge_Grise);
        } else if (CaseAssociee.PresenceCaseVide() == true) {
            setIcon(img_Case_Vide);
        } else {
            String couleur_pion = CaseAssociee.LireCouleurDuPion();
            switch (couleur_pion) {
                case "Rouge":
                    setIcon(img_Pion_Rouge);
                    break;
                default: // Si aucune des conditions au dessus n'est remplie, affichage du pion bleu
                    setIcon(img_Pion_Bleu);
                    break;
            }
        }
    }

}
