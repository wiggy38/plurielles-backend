package bf.orange.oguest.oguestbackend.adherant.dao.repository;

import bf.orange.oguest.oguestbackend.adherant.dao.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m WHERE m.prenoms LIKE '%:data%'")
    List<Member> searchByNomPrenomsContains(String data);

}