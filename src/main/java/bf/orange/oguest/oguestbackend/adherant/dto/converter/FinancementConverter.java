package bf.orange.oguest.oguestbackend.adherant.dto.converter;

import bf.orange.oguest.oguestbackend.adherant.dao.entity.Financement;
import bf.orange.oguest.oguestbackend.adherant.dao.repository.FinancementRepository;
import bf.orange.oguest.oguestbackend.adherant.dto.FinancementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FinancementConverter {

    @Autowired
    FinancementRepository financementRepository;

    public FinancementDto toDto(Financement financement) {
        FinancementDto financementDto = new FinancementDto();
        financementDto.setId(financement.getId());
        financementDto.setMontant(financement.getMontant());
        financementDto.setProjet(financement.getProjet());
        financementDto.setType(financement.getType());
        financementDto.setDate(financement.getDate());
        financementDto.setStatus(financement.getStatus());
        financementDto.setDeleted(financement.getDeleted());
        return financementDto;
    }

    public List<FinancementDto> toDtoList(List<Financement> financements) {
        List<FinancementDto> financementDtos = new ArrayList<>();
        for (Financement financement : financements) {
            financementDtos.add(this.toDto(financement));
        }
        return financementDtos;
    }

    public Financement fromDto(FinancementDto financementDto) {
        Financement financement = new Financement();
        financement.setId(financementDto.getId());
        financement.setMontant(financementDto.getMontant());
        financement.setProjet(financementDto.getProjet());
        financement.setType(financementDto.getType());
        financement.setDate(financementDto.getDate());
        financement.setStatus(financementDto.getStatus());
        financement.setDeleted(financementDto.getDeleted());
        return financement;
    }

    public List<Financement> fromDtoList(List<FinancementDto> financementDtos) {
        List<Financement> financements = new ArrayList<>();
        for (FinancementDto FinancementDto : financementDtos) {
            financements.add(this.fromDto(FinancementDto));
        }
        return financements;
    }

}
