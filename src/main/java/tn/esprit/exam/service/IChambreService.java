package tn.esprit.exam.service;

import tn.esprit.exam.entity.Chambre;
import tn.esprit.exam.entity.TypeChambre;

import java.util.List;

public interface IChambreService {

    public List<Chambre> retrieveAllChambres();
    public Chambre retrieveChambre(Long chambreId);
    public Chambre addChambre(Chambre c);
    public void removeChambre(Long chambreId);
    public Chambre modifyChambre(Chambre chambre);

    // Here we will add if any method calling keywords or JPQL

    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeChambre) ;
    public List<Chambre> getChambresNonReserveParNomFoyerEtTypeChambre(String nomFoyer, TypeChambre typeChambre) ;

}
