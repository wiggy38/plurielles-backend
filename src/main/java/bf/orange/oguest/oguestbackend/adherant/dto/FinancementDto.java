package bf.orange.oguest.oguestbackend.adherant.dto;

import bf.orange.oguest.oguestbackend.adherant.dao.entity.Financement;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonSerialize
public class FinancementDto implements Serializable {
    private Long id;
    private String type;
    private Double montant;
    private String projet;
    private String date;
    private Financement.Status status;
    private Boolean deleted;
}
