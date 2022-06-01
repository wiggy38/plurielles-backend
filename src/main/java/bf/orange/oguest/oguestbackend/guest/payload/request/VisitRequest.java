package bf.orange.oguest.oguestbackend.guest.payload.request;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Visit;
import lombok.Data;

import java.io.Serializable;

@Data
public class VisitRequest implements Serializable {
    private final Long id;
    private final String arrival_time;
    private final String departure_time;
    private final String motive;
    private final String comment;
    private final Visit.Status status;
    private final Long type_visitId;
    private final Long badgeId;
    private final Long visitorId;
    private final Long employeeId;
}
