package bf.orange.oguest.oguestbackend.guest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeDto implements Serializable {
    private final Long id;
    private final String nom;
    private final String prenoms;
    private final String cuid;
    private final String email;
    private final String phone;
    private final DepartmentDto department;
    private final LocationDto location;
}
