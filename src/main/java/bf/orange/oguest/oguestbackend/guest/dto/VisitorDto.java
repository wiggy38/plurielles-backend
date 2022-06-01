package bf.orange.oguest.oguestbackend.guest.dto;

import bf.orange.oguest.oguestbackend.guest.dao.entity.Visitor;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonSerialize
public class VisitorDto implements Serializable {
    private Long id;
    private String nom;
    private String prenoms;
    private String numeroCnib;
    private String dateValidite;
    private String typePiece;
    private Visitor.Status statut;
}
