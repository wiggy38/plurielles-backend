package bf.orange.oguest.oguestbackend.guest.business;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Visit;
import bf.orange.oguest.oguestbackend.guest.dao.repository.VisitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class VisitBusiness {

    @Autowired
    VisitRepository visitRepository;

    public List<Visit> findAll() {
        List<Visit> visits = visitRepository.findAll();
        return visits;
    }

    public Visit findById(Long id) {
        Visit visit = findById(id);
        return visit;
    }

    public List<Visit> saveAll(List<Visit> visits) {
        List<Visit> visits1 = visitRepository.saveAll(visits);
        return visits1;
    }

    public void softDeleteAll(List<Long> ids) {
        List<Visit> visits = new ArrayList<>();
        for (Long id:ids) {
            Visit l = visitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            l.setDeleted(true);
            visits.add(l);
        }
        visitRepository.saveAll(visits);
    }

    public void deleteAll(List<Long> ids) {
        List<Visit> visits = new ArrayList<>();
        for (Long id:ids) {
            Visit l = visitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            visits.add(l);
        }
        visitRepository.deleteAll(visits);
    }

}
