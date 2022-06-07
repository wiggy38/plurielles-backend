package bf.orange.oguest.oguestbackend.guest.payload.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class BadgeRequest implements Serializable {
    private final Long id;
    private final String numeroBadge;
    private final Long departmentId;
}
