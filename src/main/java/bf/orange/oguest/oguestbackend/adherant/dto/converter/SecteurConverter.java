package bf.orange.oguest.oguestbackend.adherant.dto.converter;

import bf.orange.oguest.oguestbackend.adherant.dao.entity.Secteur;
import bf.orange.oguest.oguestbackend.adherant.dao.repository.SecteurRepository;
import bf.orange.oguest.oguestbackend.adherant.dto.SecteurDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecteurConverter {
    @Autowired
    SecteurRepository secteurRepository;

    public SecteurDto toDto(Secteur secteur) {
        SecteurDto secteurDto = new SecteurDto();
        secteurDto.setId(secteur.getId());
        secteurDto.setLibelle(secteur.getLibelle());
        return secteurDto;
    }

    public List<SecteurDto> toDtoList(List<Secteur> secteurs) {
        List<SecteurDto> secteurDtos = new ArrayList<>();
        for (Secteur secteur : secteurs) {
            secteurDtos.add(this.toDto(secteur));
        }
        return secteurDtos;
    }

    public Secteur fromDto(SecteurDto secteurDto) {
        Secteur secteur = new Secteur();
        secteur.setId(secteurDto.getId());
        secteur.setLibelle(secteurDto.getLibelle());
        return secteur;
    }

    public List<Secteur> fromDtoList(List<SecteurDto> secteurDtos) {
        List<Secteur> secteurs = new ArrayList<>();
        for (SecteurDto SecteurDto : secteurDtos) {
            secteurs.add(this.fromDto(SecteurDto));
        }
        return secteurs;
    }

}
