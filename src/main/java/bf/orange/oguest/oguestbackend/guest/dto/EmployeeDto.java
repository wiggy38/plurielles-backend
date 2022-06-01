package bf.orange.oguest.oguestbackend.guest.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonSerialize
public class EmployeeDto implements Serializable {
    private Long id;
    private String nom;
    private String prenoms;
    private String cuid;
    private String email;
    private String phone;
    private DepartmentDto department;
    private LocationDto location;
}
