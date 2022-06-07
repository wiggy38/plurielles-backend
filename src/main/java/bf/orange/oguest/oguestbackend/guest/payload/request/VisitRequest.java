package bf.orange.oguest.oguestbackend.guest.payload.request;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Visit;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class VisitRequest implements Serializable {
    private final Long id;
    private final Date arrivalDate;
    private final String arrivalTime;
    private final String departureTime;
    private final String motive;
    private final String comment;
    private final String status;
    private final Long typeVisitId;
    private final Long badgeId;
    private final Long visitorId;
    private final Long employeeId;
}
