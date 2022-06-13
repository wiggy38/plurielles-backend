package bf.orange.oguest.oguestbackend.adherant.controller;

import bf.orange.oguest.oguestbackend.adherant.business.FormulaBusiness;
import bf.orange.oguest.oguestbackend.adherant.dao.entity.Formula;
import bf.orange.oguest.oguestbackend.adherant.dto.FormulaDto;
import bf.orange.oguest.oguestbackend.adherant.dto.converter.FormulaConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/formula/")
@Slf4j
public class FormulaController {

    @Autowired
    FormulaBusiness formulaBusiness;
    @Autowired
    FormulaConverter formulaConverter;

    @GetMapping("/r/list/all")
    public List<FormulaDto> getAllBadges() {
        List<Formula> formulas = formulaBusiness.findAll();
        return formulaConverter.toDtoList(formulas);
    }

    @PutMapping("/w/update/{id}")
    public ResponseEntity<List<FormulaDto>> update(@RequestBody FormulaDto formulaDto) {
        Formula formula = formulaConverter.fromDto(formulaDto);
        List<Formula> formulas = List.of(formula);
        List<Formula> savedFormulas = formulaBusiness.saveAll(formulas);
        return ResponseEntity.ok().body(formulaConverter.toDtoList(savedFormulas));
    }

    @PostMapping("/w/insert/new")
    public ResponseEntity<List<FormulaDto>> create(@RequestBody List<FormulaDto> formulaDtos) throws Exception {
        List<Formula> formulas = new ArrayList<Formula>(formulaConverter.fromDtoList(formulaDtos));
        List<Formula> savedFormulas = formulaBusiness.saveAll(formulas);
        if(savedFormulas == null) {
            throw new Exception("Une erreur s'est produite. Veuillez réessayer plus tard.");
        }
        return ResponseEntity.ok().body(formulaConverter.toDtoList(savedFormulas));
    }

    @PostMapping("/d/delete/badges")
    public void deleteAll(@PathVariable("id") List<Long> ids) {
        formulaBusiness.deleteAll(ids);
    }


}
