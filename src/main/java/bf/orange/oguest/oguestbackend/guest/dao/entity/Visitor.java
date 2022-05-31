package bf.orange.oguest.oguestbackend.guest.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "visiteur")
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenoms;

    @Column(nullable = false)
    private String numero_cnib;

    @Column(nullable = false)
    private String date_validite;

    @Column(nullable = false)
    private String type_piece;

    @Column(nullable = false)
    private String statut;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

}