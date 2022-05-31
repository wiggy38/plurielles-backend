package bf.orange.oguest.oguestbackend.guest.dto;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Visit;
import lombok.Data;

import java.io.Serializable;

@Data
public class VisitDto implements Serializable {
    private final Long id;
    private final String arrival_time;
    private final String departure_time;
    private final String motive;
    private final String comment;
    private final Visit.Status status;
    private final TypeVisitDto type_visit;
    private final BadgeDto badge;
    private final VisitorDto visitor;
    private final EmployeeDto employee;
}
