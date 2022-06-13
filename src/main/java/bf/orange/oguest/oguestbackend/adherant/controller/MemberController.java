package bf.orange.oguest.oguestbackend.adherant.controller;

import bf.orange.oguest.oguestbackend.adherant.business.MemberBusiness;
import bf.orange.oguest.oguestbackend.adherant.dao.entity.Member;
import bf.orange.oguest.oguestbackend.adherant.dto.MemberDto;
import bf.orange.oguest.oguestbackend.adherant.dto.converter.MemberConverter;
import bf.orange.oguest.oguestbackend.adherant.payload.request.MemberRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/member/")
@Slf4j
public class MemberController {

    @Autowired
    MemberBusiness memberBusiness;
    @Autowired
    MemberConverter memberConverter;

    @GetMapping("/r/list/all")
    public List<MemberDto> getAllEmployees() {
        List<Member> members = memberBusiness.findAll();
        return memberConverter.toDtoList(members);
    }

    @GetMapping("/r/single/entry/{id}")
    public MemberDto getOneMember(@PathVariable String id) {
        log.error(">>>>>>>>>> DATA "+id);
        Member member = memberBusiness.findById(Long.parseLong(id));
        return memberConverter.toDto(member);
    }

    @GetMapping("/r/list/searchBy/name/{data}")
    public List<MemberDto> searchAllEmployeesByName(@PathVariable String data) {
        log.error(">>>>>>>>>> DATA "+data);
        List<Member> members = memberBusiness.searchByNomPrenomsContains(data);
        return memberConverter.toDtoList(members);
    }

    @PutMapping("/w/update/{id}")
    public ResponseEntity<List<MemberDto>> update(@RequestBody MemberRequest memberRequest) {
        Member member = memberConverter.fromRequest(memberRequest);
        List<Member> members = List.of(member);
        List<Member> savedMembers = memberBusiness.saveAll(members);
        return ResponseEntity.ok().body(memberConverter.toDtoList(savedMembers));
    }

    @PostMapping("/w/insert/new")
    public ResponseEntity<List<MemberDto>> create(@RequestBody MemberRequest memberRequest) throws Exception {
        System.out.println(memberRequest);
        List<Member> members = Arrays.asList(memberConverter.fromRequest(memberRequest));
        List<Member> savedMembers = memberBusiness.saveAll(members);
        if(savedMembers == null) {
            throw new Exception("Une erreur s'est produite. Veuillez réessayer plus tard.");
        }
        return ResponseEntity.ok().body(memberConverter.toDtoList(savedMembers));
    }

    @PostMapping("/d/delete/employees")
    public void deleteAll(@PathVariable("id") List<Long> ids) {
        memberBusiness.deleteAll(ids);
    }

}
