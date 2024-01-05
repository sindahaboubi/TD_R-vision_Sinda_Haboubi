package classes;

import java.util.*;

import exceptions.VoitureException;
import interfaces.Critere;

public class Agence {
    private String nom;
    private ListVoitures vs;
    private Map<Client, ListVoitures> clientVoitureLoue;
    
    public Agence(String nom) {
        this.nom = nom;
        this.vs = new ListVoitures();
        this.clientVoitureLoue = new HashMap<>();
    }
    
    public ListVoitures getVoitures() {
        return vs;
    }
    
    public void ajoutVoiture(Voiture v) throws VoitureException {
        vs.ajoutVoiture(v);
    }

    public void suppVoiture(Voiture v) throws VoitureException {
        vs.supprimeVoiture(v);
    }
    
    public void loueClientVoiture(Client cl, Voiture v) throws VoitureException {
        vs.supprimeVoiture(v);
        clientVoitureLoue.computeIfAbsent(cl, k -> new ListVoitures()).ajoutVoiture(v);
    }
    
    public void retourClientVoiture(Client cl, Voiture v) throws VoitureException {
        clientVoitureLoue.get(cl).supprimeVoiture(v);
        vs.ajoutVoiture(v);
    }
    
    public List<Voiture> selectVoitureSelonCritere(Critere c) {
        List<Voiture> result = new ArrayList<>();
        for (Voiture v : vs.getVoitures()) {
            if (c.estSatisfaitPar(v)) {
                result.add(v);
            }
        }
        return result;
    }
    
    public Set<Client> ensembleClientsLoueurs() {
        return clientVoitureLoue.keySet();
    }
    
    public Collection<ListVoitures> collectionVoituresLouees() {
        return clientVoitureLoue.values();
    }
    
    public void afficheLesClientsEtLeursListesVoitures() {
        for (Map.Entry<Client, ListVoitures> entry : clientVoitureLoue.entrySet()) {
            System.out.println("Client: " + entry.getKey().getNom() + " " + entry.getKey().getPrenom());
            entry.getValue().affiche();
            System.out.println();
        }
    }
    
    public Map<Client, ListVoitures> triCodeCroissant() {
        return new TreeMap<>(clientVoitureLoue);
    }
    
    public Map<Client, ListVoitures> triNomCroissant() {
        Map<Client, ListVoitures> result = new TreeMap<>(Comparator.comparing(Client::getNom));
        result.putAll(clientVoitureLoue);
        return result;
    }
}
