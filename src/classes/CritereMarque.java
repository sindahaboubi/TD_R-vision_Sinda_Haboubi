package classes;

import interfaces.Critere;

public class CritereMarque implements Critere {
	private String marque;
	
    public CritereMarque(String marque) {
        this.marque = marque;
    }

    public boolean estSatisfaitPar(Voiture v) {
        return v.getMarque().equalsIgnoreCase(marque);
    }
}
