package bf.orange.oguest.oguestbackend.guest.business;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Visitor;
import bf.orange.oguest.oguestbackend.guest.dao.repository.VisitorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class VisitorBusiness {

    @Autowired
    VisitorRepository visitorRepository;

    public List<Visitor> findAll() {
        List<Visitor> visitors = visitorRepository.findAll();
        return visitors;
    }

    public Visitor findById(Long id) {
        Visitor visitor = findById(id);
        return visitor;
    }

    public List<Visitor> saveAll(List<Visitor> visitors) {
        List<Visitor> visitors1 = visitorRepository.saveAll(visitors);
        return visitors1;
    }

    public void softDeleteAll(List<Long> ids) {
        List<Visitor> visitors = new ArrayList<>();
        for (Long id:ids) {
            Visitor l = visitorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            l.setDeleted(true);
            visitors.add(l);
        }
        visitorRepository.saveAll(visitors);
    }

    public void deleteAll(List<Long> ids) {
        List<Visitor> visitors = new ArrayList<>();
        for (Long id:ids) {
            Visitor l = visitorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            visitors.add(l);
        }
        visitorRepository.deleteAll(visitors);
    }
    
}
