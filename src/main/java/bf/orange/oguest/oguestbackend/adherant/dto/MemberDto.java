package bf.orange.oguest.oguestbackend.adherant.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonSerialize
public class MemberDto implements Serializable {
    private Long id;
    private String nom;
    private String prenoms;
    private String refIdentite;
    private String situationFamiliale;
    private String email;
    private String phone;
    private Boolean active;
    private Boolean deleted;
    private String quartier;
    private String address;
    private String profession;
    private String city;
    private String picture;
    private Boolean existJuridiq;
    private String docJuridiq;
    private Date created;
    private Date updated;
    private CategoryDto category;
    private FormulaDto formula;
    private SecteurDto secteur;

}
