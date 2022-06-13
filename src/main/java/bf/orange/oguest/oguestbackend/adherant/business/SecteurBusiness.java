package bf.orange.oguest.oguestbackend.adherant.business;

import bf.orange.oguest.oguestbackend.adherant.dao.entity.Secteur;
import bf.orange.oguest.oguestbackend.adherant.dao.repository.SecteurRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SecteurBusiness {

    @Autowired
    SecteurRepository secteurRepository;

    public List<Secteur> findAll() {
        List<Secteur> secteurs = secteurRepository.findAll();
        return secteurs;
    }

    public Secteur findById(Long id) {
        Secteur secteur = findById(id);
        return secteur;
    }

    public List<Secteur> saveAll(List<Secteur> secteurs) {
        List<Secteur> typeVisits1 = secteurRepository.saveAll(secteurs);
        return typeVisits1;
    }

    public void softDeleteAll(List<Long> ids) {
        List<Secteur> secteurs = new ArrayList<>();
        for (Long id:ids) {
            Secteur l = secteurRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            l.setDeleted(true);
            secteurs.add(l);
        }
        secteurRepository.saveAll(secteurs);
    }

    public void deleteAll(List<Long> ids) {
        List<Secteur> secteurs = new ArrayList<>();
        for (Long id:ids) {
            Secteur l = secteurRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            secteurs.add(l);
        }
        secteurRepository.deleteAll(secteurs);
    }
    
}
