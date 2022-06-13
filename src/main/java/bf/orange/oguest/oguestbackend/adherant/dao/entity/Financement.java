package bf.orange.oguest.oguestbackend.adherant.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "finanment")
public class Financement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Double montant;

    @Column(nullable = false)
    private String projet;

    @Column(nullable = false)
    private String date;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NONE;

    private Boolean deleted = false;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    public enum Status {
        NONE, UPCOMMING, ONGOING, COMPLETED, RECEIVED;
    }

}