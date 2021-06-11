package atl.repo;

import atl.model.Proba;

public interface RepositoryProbe extends Repository<Long, Proba>{
    Proba findProbaByDescriere(String descriere);

    int updateProba(long id, Proba proba);

    int deleteProba(long id);
}
