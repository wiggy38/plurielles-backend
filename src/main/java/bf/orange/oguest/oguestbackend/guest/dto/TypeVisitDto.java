package bf.orange.oguest.oguestbackend.guest.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonSerialize
public class TypeVisitDto implements Serializable {
    private Long id;
    private String libelle;
}
