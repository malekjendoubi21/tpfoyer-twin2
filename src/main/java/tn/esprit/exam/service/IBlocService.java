package tn.esprit.exam.service;

import tn.esprit.exam.entity.Bloc;

import java.util.List;
public interface IBlocService {
    List<Bloc> retrieveBlocs();
    Bloc updateBloc (Bloc bloc);
    Bloc addBloc (Bloc bloc);
    Bloc retrieveBloc (long idBloc);
    void removeBloc (long idBloc);
     Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) ;
     Bloc affecterBlocAFoyer(long idBloc, long idFoyer) ;

}
