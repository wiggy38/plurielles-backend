package bf.orange.oguest.oguestbackend.adherant.dto.converter;

import bf.orange.oguest.oguestbackend.adherant.business.CategoryBusiness;
import bf.orange.oguest.oguestbackend.adherant.dao.entity.Formula;
import bf.orange.oguest.oguestbackend.adherant.dao.repository.FormulaRepository;
import bf.orange.oguest.oguestbackend.adherant.dto.FormulaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FormulaConverter {

    @Autowired
    FormulaRepository formulaRepository;
    @Autowired
    CategoryConverter categoryConverter;
    @Autowired
    CategoryBusiness categoryBusiness;

    public FormulaDto toDto(Formula formula) {
        FormulaDto formulaDto = new FormulaDto();
        formulaDto.setId(formula.getId());
        formulaDto.setNumeroBadge(formula.getLibelle());
        formulaDto.setSlug(formula.getSlug());
        return formulaDto;
    }

    public List<FormulaDto> toDtoList(List<Formula> formulas) {
        List<FormulaDto> formulaDtos = new ArrayList<>();
        for (Formula formula : formulas) {
            formulaDtos.add(this.toDto(formula));
        }
        return formulaDtos;
    }

    public Formula fromDto(FormulaDto formulaDto) {
        Formula formula = new Formula();
        formula.setId(formulaDto.getId());
        formula.setLibelle(formula.getLibelle());
        formula.setSlug(formula.getSlug());
        return formula;
    }

    public List<Formula> fromDtoList(List<FormulaDto> formulaDtos) {
        List<Formula> formulas = new ArrayList<>();
        for (FormulaDto formulaDto : formulaDtos) {
            formulas.add(this.fromDto(formulaDto));
        }
        return formulas;
    }

}
