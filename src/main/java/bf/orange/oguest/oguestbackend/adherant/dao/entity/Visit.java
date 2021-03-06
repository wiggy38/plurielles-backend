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
@Table(name = "visites")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Date arrivalDate;

    @Column(nullable = false)
    private String arrivalTime;

    @Column(nullable = false)
    private String departureTime;

    @Column(nullable = false)
    private String motive;

    @Column(nullable = false)
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private Boolean deleted = false;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "type_visit_id")
    private Secteur secteur;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "badge_id")
    private Formula formula;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "visitor_id")
    private Financement financement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "employee_id")
    private Member member;

    public enum Status {
        EXPECTED, ONGOING, COMPLETED;
    }

}