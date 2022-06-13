package bf.orange.oguest.oguestbackend.adherant.dto.converter;

import bf.orange.oguest.oguestbackend.adherant.business.*;
import bf.orange.oguest.oguestbackend.adherant.dao.entity.Visit;
import bf.orange.oguest.oguestbackend.adherant.dao.repository.VisitRepository;
import bf.orange.oguest.oguestbackend.adherant.dto.VisitDto;
import bf.orange.oguest.oguestbackend.adherant.payload.request.VisitRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VisitConverter {

    @Autowired
    VisitRepository visitRepository;
    @Autowired
    FormulaConverter formulaConverter;
    @Autowired
    FinancementConverter financementConverter;
    @Autowired
    SecteurConverter secteurConverter;
    @Autowired
    MemberBusiness memberBusiness;
    @Autowired
    FinancementBusiness financementBusiness;
    @Autowired
    SecteurBusiness secteurBusiness;
    @Autowired
    FormulaBusiness formulaBusiness;

    Visit setNew() {
        Visit visit = new Visit();
        visit.setDeleted(false);
        return visit;
    }

    public VisitDto toDto(Visit visit) {
        VisitDto visitDto = new VisitDto();
        visitDto.setId(visit.getId());
        visitDto.setArrivalTime(visit.getArrivalTime());
        visitDto.setDepartureTime(visit.getDepartureTime());
        visitDto.setMotive(visit.getMotive());
        visitDto.setComment(visit.getComment());
        visitDto.setStatus(visit.getStatus());
        visitDto.setBadge(formulaConverter.toDto(visit.getFormula()));
        visitDto.setVisitor(financementConverter.toDto(visit.getFinancement()));
        visitDto.setTypeVisit(secteurConverter.toDto(visit.getSecteur()));
        return visitDto;
    }

    public List<VisitDto> toDtoList(List<Visit> visits) {
        List<VisitDto> visitDtos = new ArrayList<>();
        for (Visit visit:visits) {
            visitDtos.add(this.toDto(visit));
        }
        return visitDtos;
    }

    public Visit fromDto(VisitDto visitDto) {
        Visit visit = this.setNew();
        visit.setId(visitDto.getId());
        visit.setArrivalTime(visitDto.getArrivalTime());
        visit.setDepartureTime(visitDto.getDepartureTime());
        visit.setMotive(visitDto.getMotive());
        visit.setComment(visitDto.getComment());
        visit.setStatus(visitDto.getStatus());
        visit.setFormula(formulaConverter.fromDto(visitDto.getBadge()));
        visit.setFinancement(financementConverter.fromDto(visitDto.getVisitor()));
        visit.setSecteur(secteurConverter.fromDto(visitDto.getTypeVisit()));
        return visit;
    }

    public List<Visit> fromDtoList(List<VisitDto> visitDtos) {
        List<Visit> Visits = new ArrayList<>();
        for (VisitDto VisitDto : visitDtos) {
            Visits.add(this.fromDto(VisitDto));
        }
        return Visits;
    }

    public Visit fromRequest(VisitRequest visitRequest) {
        Visit visit = this.setNew();
        visit.setId(visitRequest.getId());
        visit.setMember(memberBusiness.findById(visitRequest.getEmployeeId()));
        visit.setFinancement(financementBusiness.findById(visitRequest.getVisitorId()));
        visit.setSecteur(secteurBusiness.findById(visitRequest.getTypeVisitId()));
        visit.setFormula(formulaBusiness.findById(visitRequest.getBadgeId()));
        visit.setComment(visitRequest.getComment());
        visit.setArrivalDate(visitRequest.getArrivalDate());
        visit.setArrivalTime(visitRequest.getArrivalTime());
        visit.setDepartureTime(visitRequest.getDepartureTime());
        visit.setMotive(visitRequest.getMotive());
        visit.setStatus(Visit.Status.valueOf(visitRequest.getStatus()));
        visit.setDeleted(false);
        return visit;
    }

}
