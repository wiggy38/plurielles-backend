package bf.orange.oguest.oguestbackend.adherant.payload.request;

import bf.orange.oguest.oguestbackend.adherant.dao.entity.Category;
import bf.orange.oguest.oguestbackend.adherant.dao.entity.Formula;
import bf.orange.oguest.oguestbackend.adherant.dto.CategoryDto;
import bf.orange.oguest.oguestbackend.adherant.dto.FormulaDto;
import bf.orange.oguest.oguestbackend.adherant.dto.SecteurDto;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
public class MemberRequest implements Serializable {
    private Long id;
    private String nom;
    private String prenoms;
    private String refIdentite;
    private String situationFamiliale;
    private String email;
    private String phone;
    private String quartier;
    private String address;
    private String profession;
    private String city;
    private String picture;
    private Boolean existJuridiq;
    private String docJuridiq;
    private Long categoryId;
    private Long formulaId;
    private Long secteurId;

}
