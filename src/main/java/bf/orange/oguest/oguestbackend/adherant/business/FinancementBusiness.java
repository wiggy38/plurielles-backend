package bf.orange.oguest.oguestbackend.adherant.business;

import bf.orange.oguest.oguestbackend.adherant.dao.entity.Financement;
import bf.orange.oguest.oguestbackend.adherant.dao.repository.FinancementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FinancementBusiness {

    @Autowired
    FinancementRepository financementRepository;

    public List<Financement> findAll() {
        List<Financement> financements = financementRepository.findAll();
        return financements;
    }

    public Financement findById(Long id) {
        Financement financement = findById(id);
        return financement;
    }

    public List<Financement> saveAll(List<Financement> financements) {
        List<Financement> visitors1 = financementRepository.saveAll(financements);
        return visitors1;
    }

    public void softDeleteAll(List<Long> ids) {
        List<Financement> financements = new ArrayList<>();
        for (Long id:ids) {
            Financement l = financementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            l.setDeleted(true);
            financements.add(l);
        }
        financementRepository.saveAll(financements);
    }

    public void deleteAll(List<Long> ids) {
        List<Financement> financements = new ArrayList<>();
        for (Long id:ids) {
            Financement l = financementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            financements.add(l);
        }
        financementRepository.deleteAll(financements);
    }
    
}
