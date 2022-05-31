package bf.orange.oguest.oguestbackend.guest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BadgeDto implements Serializable {
    private final Long id;
    private final String numero_badge;
    private final DepartmentDto department;
}
