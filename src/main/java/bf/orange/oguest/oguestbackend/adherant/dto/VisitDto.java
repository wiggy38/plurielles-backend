package bf.orange.oguest.oguestbackend.adherant.dto;

import bf.orange.oguest.oguestbackend.adherant.dao.entity.Visit;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@JsonSerialize
public class VisitDto implements Serializable {
    private Long id;
    private Date arrivalDate;
    private String arrivalTime;
    private String departureTime;
    private String motive;
    private String comment;
    private Visit.Status status;
    private SecteurDto typeVisit;
    private FormulaDto badge;
    private FinancementDto visitor;
    private MemberDto employee;
}
