package bf.orange.oguest.oguestbackend.guest.dto.converter;

import bf.orange.oguest.oguestbackend.guest.business.DepartmentBusiness;
import bf.orange.oguest.oguestbackend.guest.dao.entity.Badge;
import bf.orange.oguest.oguestbackend.guest.dao.repository.BadgeRepository;
import bf.orange.oguest.oguestbackend.guest.dto.BadgeDto;
import bf.orange.oguest.oguestbackend.guest.payload.request.BadgeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BadgeConverter {

    @Autowired
    BadgeRepository badgeRepository;
    @Autowired
    DepartmentConverter departmentConverter;
    @Autowired
    DepartmentBusiness departmentBusiness;

    public BadgeDto toDto(Badge badge) {
        BadgeDto badgeDto = new BadgeDto();
        badgeDto.setId(badge.getId());
        badgeDto.setNumeroBadge(badge.getNumeroBadge());
        badgeDto.setDepartment(departmentConverter.toDto(badge.getDepartment()));
        return badgeDto;
    }

    public List<BadgeDto> toDtoList(List<Badge> badges) {
        List<BadgeDto> badgeDtos = new ArrayList<>();
        for (Badge badge:badges) {
            badgeDtos.add(this.toDto(badge));
        }
        return badgeDtos;
    }

    public Badge fromDto(BadgeDto badgeDto) {
        Badge badge = new Badge();
        badge.setId(badgeDto.getId());
        badge.setNumeroBadge(badge.getNumeroBadge());
        badge.setDepartment(departmentConverter.fromDto(badgeDto.getDepartment()));
        return badge;
    }

    public List<Badge> fromDtoList(List<BadgeDto> badgeDtos) {
        List<Badge> badges = new ArrayList<>();
        for (BadgeDto badgeDto:badgeDtos) {
            badges.add(this.fromDto(badgeDto));
        }
        return badges;
    }

    public Badge fromRequest(BadgeRequest badgeRequest) {
        Badge badge = new Badge();
        badge.setId(badgeRequest.getId());
        badge.setNumeroBadge(badgeRequest.getNumeroBadge());
        badge.setDepartment(departmentBusiness.findById(badgeRequest.getDepartmentId()));
        return badge;
    }

    public List<Badge> fromRequestList(List<BadgeRequest> badgeRequests) {
        List<Badge> badges = new ArrayList<>();
        for (BadgeRequest badgeRequest:badgeRequests) {
            badges.add(this.fromRequest(badgeRequest));
        }
        return badges;
    }

}
