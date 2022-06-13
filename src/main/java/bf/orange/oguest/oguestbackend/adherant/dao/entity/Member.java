package bf.orange.oguest.oguestbackend.adherant.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenoms;

    private String refIdentite;

    @Column(nullable = false)
    private String situationFamiliale;

    private String email;

    private String phone;

    private Boolean active = true;

    private Boolean deleted = false;

    private String quartier;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String profession;

    @Column(nullable = true)
    private String city;

    @Column(nullable = true)
    private String picture;

    @Column(nullable = true)
    private Boolean existJuridiq;

    @Column(nullable = true)
    private String docJuridiq;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "department_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "formula_id")
    private Formula formula;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "secteur_id")
    private Secteur secteur;

}