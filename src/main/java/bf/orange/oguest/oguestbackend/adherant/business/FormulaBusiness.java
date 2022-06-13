package bf.orange.oguest.oguestbackend.adherant.business;

import bf.orange.oguest.oguestbackend.adherant.dao.entity.Formula;
import bf.orange.oguest.oguestbackend.adherant.dao.repository.FormulaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FormulaBusiness {

    @Autowired
    FormulaRepository formulaRepository;

    public List<Formula> findAll() {
        List<Formula> formulas = formulaRepository.findAll();
        return formulas;
    }

    public Formula findById(Long id) {
        Formula formula = formulaRepository.findById(id).get();
        return formula;
    }

    public List<Formula> saveAll(List<Formula> formulas) {
        List<Formula> badges1 = formulaRepository.saveAll(formulas);
        return badges1;
    }

    public void softDeleteAll(List<Long> ids) {
        List<Formula> formulas = new ArrayList<>();
        for (Long id:ids) {
            Formula l = formulaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            l.setDeleted(true);
            formulas.add(l);
        }
        formulaRepository.saveAll(formulas);
    }

    public void deleteAll(List<Long> ids) {
        List<Formula> formulas = new ArrayList<>();
        for (Long id:ids) {
            Formula l = formulaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            formulas.add(l);
        }
        formulaRepository.deleteAll(formulas);
    }

}
