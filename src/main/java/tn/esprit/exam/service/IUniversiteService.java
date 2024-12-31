package tn.esprit.exam.service;
import tn.esprit.exam.entity.Universite;

import java.util.List;

public interface    IUniversiteService {
    List<Universite> retrieveAllUniversities();
    Universite addUniversite (Universite u);
    Universite updateUniversite (Universite u);
    Universite retrieveUniversite (long idUniversite);
    void removeUniversite (long idUniversite);
    public Universite affecterFoyerAUniversite (long idFoyer, String nomUniversite) ;
    public Universite desaffecterFoyerAUniversite (long idUniversite) ;

    }
