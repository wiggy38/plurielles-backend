package bf.orange.oguest.oguestbackend.auth.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="roles")
public class Role {

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------    @Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------
    @Column(name = "nom", nullable = false, length = 32)
    private String nom;

    @Column(name = "slug", nullable = false, length = 24)
    private String slug;

    @Column(name = "defaut", nullable = false, length = 24)
    private Boolean defaut = false;

    public Role(String nom, String slug, Boolean defaut) {
        this.nom = nom;
        this.slug = slug;
        this.defaut = defaut;
    }

}
