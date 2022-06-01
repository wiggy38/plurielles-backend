package bf.orange.oguest.oguestbackend.guest.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "type_visiteur")
public class TypeVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String libelle;

    private Boolean deleted = false;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

}