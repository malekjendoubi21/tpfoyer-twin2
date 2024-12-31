package tn.esprit.exam.service;

import tn.esprit.exam.entity.Foyer;

import java.util.List;

public interface IFoyerService {
    List<Foyer> retrieveAllFoyers();
    Foyer addFoyer (Foyer f);
    Foyer updateFoyer (Foyer f);
    Foyer retrieveFoyer (long idFoyer);
    void removeFoyer (long idFoyer);
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) ;

}
