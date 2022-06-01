package bf.orange.oguest.oguestbackend.guest.payload.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class BadgeRequest implements Serializable {
    private final String numero_badge;
    private final Long departmentId;
}
