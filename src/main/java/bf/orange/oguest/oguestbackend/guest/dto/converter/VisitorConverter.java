package bf.orange.oguest.oguestbackend.guest.dto.converter;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Visitor;
import bf.orange.oguest.oguestbackend.guest.dao.repository.VisitorRepository;
import bf.orange.oguest.oguestbackend.guest.dto.VisitorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VisitorConverter {

    @Autowired
    VisitorRepository visitorRepository;

    public VisitorDto toDto(Visitor visitor) {
        VisitorDto visitorDto = new VisitorDto();
        visitorDto.setId(visitor.getId());
        visitorDto.setNom(visitor.getNom());
        visitorDto.setPrenoms(visitor.getPrenoms());
        visitorDto.setNumeroCnib(visitor.getNumeroCnib());
        visitorDto.setDateValidite(visitor.getDateValidite());
        visitorDto.setTypePiece(visitor.getTypePiece());
        visitorDto.setStatut(visitor.getStatus());
        return visitorDto;
    }

    public List<VisitorDto> toDtoList(List<Visitor> visitors) {
        List<VisitorDto> visitorDtos = new ArrayList<>();
        for (Visitor visitor:visitors) {
            visitorDtos.add(this.toDto(visitor));
        }
        return visitorDtos;
    }

    public Visitor fromDto(VisitorDto visitorDto) {
        Visitor visitor = new Visitor();
        visitor.setId(visitorDto.getId());
        visitor.setNom(visitorDto.getNom());
        visitor.setPrenoms(visitorDto.getPrenoms());
        visitor.setNumeroCnib(visitorDto.getNumeroCnib());
        visitor.setDateValidite(visitorDto.getDateValidite());
        visitor.setTypePiece(visitorDto.getTypePiece());
        visitor.setStatus(visitorDto.getStatut());
        return visitor;
    }

    public List<Visitor> fromDtoList(List<VisitorDto> VisitorDtos) {
        List<Visitor> Visitors = new ArrayList<>();
        for (VisitorDto VisitorDto:VisitorDtos) {
            Visitors.add(this.fromDto(VisitorDto));
        }
        return Visitors;
    }

}
