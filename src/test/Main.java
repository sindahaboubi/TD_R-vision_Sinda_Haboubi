package test;

import java.util.List;

import classes.Agence;
import classes.Client;
import classes.CritereMarque;
import classes.CriterePrix;
import classes.Voiture;
import exceptions.VoitureException;
import interfaces.Critere;

public class Main {
    public static void main(String[] args) {
        Agence agence = new Agence("Agence de location de voitures");

        try {
            agence.ajoutVoiture(new Voiture(11147, "Toyota", 45.0f));
            agence.ajoutVoiture(new Voiture(11148, "BMW", 90.0f));
            agence.ajoutVoiture(new Voiture(11149, "Haval", 120.0f));
            agence.ajoutVoiture(new Voiture(11150, "Haval", 120.0f));
        } catch (VoitureException e) {
            e.printStackTrace();
        }
        
        System.out.println("La liste initiale des voitures : ");
        List<Voiture> listeInitiale = agence.getVoitures().getVoitures();
        for (Voiture voiture : listeInitiale) {
            System.out.println(voiture);
        }

        Client c1 = new Client(101, "Haboubi", "Sinda");
        Client c2 = new Client(102, "Rabhi", "Mariem");
        Client c3 = new Client(103, "Msahli", "Aziz");
        Client c4 = new Client(104, "Halloul", "NourAllah");
        Client c5 = new Client(105, "Gharsallah", "Sirine");

        try {
            List<Voiture> voituresDisponiblesMarque = agence.selectVoitureSelonCritere(new CritereMarque("Ford"));
            if (!voituresDisponiblesMarque.isEmpty()) {
                agence.loueClientVoiture(c1, voituresDisponiblesMarque.get(0));
            } else {
                System.out.println("Aucune voiture disponible avec la marque 'Ford'");
            }

            List<Voiture> voituresDisponiblesPrix = agence.selectVoitureSelonCritere(new CriterePrix(60));
            if (!voituresDisponiblesPrix.isEmpty()) {
                agence.loueClientVoiture(c2, voituresDisponiblesPrix.get(0));
            } else {
                System.out.println("Aucune voiture disponible avec un prix inférieur à 60");
            }
        } catch (VoitureException e) {
            e.printStackTrace();
        }

        System.out.println("Les clients et leurs voitures : ");
        agence.afficheLesClientsEtLeursListesVoitures();
    }
}
