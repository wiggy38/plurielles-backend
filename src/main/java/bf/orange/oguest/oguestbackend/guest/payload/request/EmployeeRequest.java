package bf.orange.oguest.oguestbackend.guest.payload.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeRequest implements Serializable {
    private final Long id;
    private final String nom;
    private final String prenoms;
    private final String cuid;
    private final String email;
    private final String phone;
    private final Long departmentId;
    private final Long locationId;
}
