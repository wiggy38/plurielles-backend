package bf.orange.oguest.oguestbackend.guest.business;

import bf.orange.oguest.oguestbackend.guest.dao.entity.TypeVisit;
import bf.orange.oguest.oguestbackend.guest.dao.repository.TypeVisitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TypeVisitBusiness {

    @Autowired
    TypeVisitRepository typeVisitRepository;

    public List<TypeVisit> findAll() {
        List<TypeVisit> typeVisits = typeVisitRepository.findAll();
        return typeVisits;
    }

    public TypeVisit findById(Long id) {
        TypeVisit typeVisit = findById(id);
        return typeVisit;
    }

    public List<TypeVisit> saveAll(List<TypeVisit> typeVisits) {
        List<TypeVisit> typeVisits1 = typeVisitRepository.saveAll(typeVisits);
        return typeVisits1;
    }

    public void softDeleteAll(List<Long> ids) {
        List<TypeVisit> typeVisits = new ArrayList<>();
        for (Long id:ids) {
            TypeVisit l = typeVisitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            l.setDeleted(true);
            typeVisits.add(l);
        }
        typeVisitRepository.saveAll(typeVisits);
    }

    public void deleteAll(List<Long> ids) {
        List<TypeVisit> typeVisits = new ArrayList<>();
        for (Long id:ids) {
            TypeVisit l = typeVisitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            typeVisits.add(l);
        }
        typeVisitRepository.deleteAll(typeVisits);
    }
    
}
