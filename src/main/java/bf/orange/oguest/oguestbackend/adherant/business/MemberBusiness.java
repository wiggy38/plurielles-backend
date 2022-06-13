package bf.orange.oguest.oguestbackend.adherant.business;

import bf.orange.oguest.oguestbackend.adherant.dao.entity.Member;
import bf.orange.oguest.oguestbackend.adherant.dao.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MemberBusiness {

    @Autowired
    MemberRepository memberRepository;

    public List<Member> findAll() {
        List<Member> members = memberRepository.findAll();
        return members;
    }

    public Member findById(Long id) {
        Member member = memberRepository.findById(id).get();
        return member;
    }

    public List<Member> searchByNomPrenomsContains(String data) {
        log.error(">>>>>>>>>> Business >>>> DATA "+data);
        List<Member> members = memberRepository.searchByNomPrenomsContains(data);
        return members;
    }

    public List<Member> saveAll(List<Member> members) {
        List<Member> employees1 = memberRepository.saveAll(members);
        return employees1;
    }

    public void softDeleteAll(List<Long> ids) {
        List<Member> members = new ArrayList<>();
        for (Long id:ids) {
            Member l = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            l.setDeleted(true);
            members.add(l);
        }
        memberRepository.saveAll(members);
    }

    public void deleteAll(List<Long> ids) {
        List<Member> members = new ArrayList<>();
        for (Long id:ids) {
            Member l = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            members.add(l);
        }
        memberRepository.deleteAll(members);
    }
}
