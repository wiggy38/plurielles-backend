package bf.orange.oguest.oguestbackend.guest.business;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Badge;
import bf.orange.oguest.oguestbackend.guest.dao.repository.BadgeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BadgeBusiness {

    @Autowired
    BadgeRepository badgeRepository;

    public List<Badge> findAll() {
        List<Badge> badges = badgeRepository.findAll();
        return badges;
    }

    public Badge findById(Long id) {
        Badge badge = findById(id);
        return badge;
    }

    public List<Badge> saveAll(List<Badge> badges) {
        List<Badge> badges1 = badgeRepository.saveAll(badges);
        return badges1;
    }

    public void softDeleteAll(List<Long> ids) {
        List<Badge> badges = new ArrayList<>();
        for (Long id:ids) {
            Badge l = badgeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            l.setDeleted(true);
            badges.add(l);
        }
        badgeRepository.saveAll(badges);
    }

    public void deleteAll(List<Long> ids) {
        List<Badge> badges = new ArrayList<>();
        for (Long id:ids) {
            Badge l = badgeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucune ressource correspondante trouvée pour "+id));
            badges.add(l);
        }
        badgeRepository.deleteAll(badges);
    }

}
