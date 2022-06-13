package bf.orange.oguest.oguestbackend.adherant.dto.converter;

import bf.orange.oguest.oguestbackend.adherant.business.CategoryBusiness;
import bf.orange.oguest.oguestbackend.adherant.business.FormulaBusiness;
import bf.orange.oguest.oguestbackend.adherant.business.LocationBusiness;
import bf.orange.oguest.oguestbackend.adherant.business.SecteurBusiness;
import bf.orange.oguest.oguestbackend.adherant.dao.entity.Member;
import bf.orange.oguest.oguestbackend.adherant.dao.repository.MemberRepository;
import bf.orange.oguest.oguestbackend.adherant.dto.MemberDto;
import bf.orange.oguest.oguestbackend.adherant.payload.request.MemberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberConverter {
    
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CategoryConverter categoryConverter;
    @Autowired
    FormulaConverter formulaConverter;
    @Autowired
    LocationConverter locationConverter;
    @Autowired
    SecteurConverter secteurConverter;
    @Autowired
    CategoryBusiness categoryBusiness;
    @Autowired
    FormulaBusiness formulaBusiness;
    @Autowired
    SecteurBusiness secteurBusiness;

    public MemberDto toDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setNom(member.getNom());
        memberDto.setPrenoms(member.getPrenoms());
        memberDto.setEmail(member.getEmail());
        memberDto.setPhone(member.getPhone());
        memberDto.setAddress(member.getAddress());
        memberDto.setCity(member.getCity());
        memberDto.setActive(true);
        memberDto.setDeleted(false);
        memberDto.setProfession(member.getProfession());
        memberDto.setQuartier(member.getQuartier());
        memberDto.setSituationFamiliale(member.getSituationFamiliale());
        memberDto.setSituationFamiliale(memberDto.getSituationFamiliale());
        memberDto.setDocJuridiq(member.getDocJuridiq());
        memberDto.setExistJuridiq(member.getExistJuridiq());
        memberDto.setRefIdentite(member.getRefIdentite());
        memberDto.setCategory(categoryConverter.toDto(member.getCategory()));
        memberDto.setFormula(formulaConverter.toDto(member.getFormula()));
        memberDto.setSecteur(secteurConverter.toDto(member.getSecteur()));
        return memberDto;
    }

    public List<MemberDto> toDtoList(List<Member> members) {
        List<MemberDto> memberDtos = new ArrayList<>();
        for (Member member : members) {
            memberDtos.add(this.toDto(member));
        }
        return memberDtos;
    }

    public Member fromDto(MemberDto memberDto) {
        Member member = new Member();
        member.setId(memberDto.getId());
        member.setNom(memberDto.getNom());
        member.setPrenoms(memberDto.getPrenoms());
        member.setEmail(memberDto.getEmail());
        member.setPhone(memberDto.getPhone());
        member.setAddress(memberDto.getAddress());
        member.setCity(memberDto.getCity());
        member.setActive(true);
        member.setDeleted(false);
        member.setProfession(memberDto.getProfession());
        member.setQuartier(memberDto.getQuartier());
        member.setSituationFamiliale(memberDto.getSituationFamiliale());
        member.setSituationFamiliale(memberDto.getSituationFamiliale());
        member.setDocJuridiq(memberDto.getDocJuridiq());
        member.setExistJuridiq(memberDto.getExistJuridiq());
        member.setRefIdentite(memberDto.getRefIdentite());
        member.setCategory(categoryBusiness.findById(memberDto.getCategory().getId()));
        member.setFormula(formulaBusiness.findById(memberDto.getFormula().getId()));
        member.setSecteur(secteurBusiness.findById(memberDto.getSecteur().getId()));
        return member;
    }

    public List<Member> fromDtoList(List<MemberDto> memberDtos) {
        List<Member> members = new ArrayList<>();
        for (MemberDto MemberDto : memberDtos) {
            members.add(this.fromDto(MemberDto));
        }
        return members;
    }

    public Member fromRequest(MemberRequest memberRequest) {
        Member member = new Member();
        member.setId(memberRequest.getId());
        member.setNom(memberRequest.getNom());
        member.setPrenoms(memberRequest.getPrenoms());
        member.setEmail(memberRequest.getEmail());
        member.setPhone(memberRequest.getPhone());
        member.setAddress(memberRequest.getAddress());
        member.setCity(memberRequest.getCity());
        member.setActive(true);
        member.setDeleted(false);
        member.setProfession(memberRequest.getProfession());
        member.setQuartier(memberRequest.getQuartier());
        member.setSituationFamiliale(memberRequest.getSituationFamiliale());
        member.setSituationFamiliale(memberRequest.getSituationFamiliale());
        member.setDocJuridiq(memberRequest.getDocJuridiq());
        member.setExistJuridiq(memberRequest.getExistJuridiq());
        member.setRefIdentite(memberRequest.getRefIdentite());
        member.setCategory(categoryBusiness.findById(memberRequest.getCategory()));
        member.setFormula(formulaBusiness.findById(memberRequest.getFormula()));
        member.setSecteur(secteurBusiness.findById(memberRequest.getSecteur()));
        return member;
    }

    public List<Member> fromRequestList(List<MemberRequest> memberRequests) {
        List<Member> members = new ArrayList<>();
        for (MemberRequest memberRequest : memberRequests) {
            members.add(this.fromRequest(memberRequest));
        }
        return members;
    }

}
