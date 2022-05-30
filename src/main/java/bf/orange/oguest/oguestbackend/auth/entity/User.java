package bf.orange.oguest.oguestbackend.auth.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

   // ----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    // ----------------------------------------------------------------------
    @NotBlank(message = "Veuillez saisir votre nom")
    @Column(name = "nom", nullable = false, length = 24)
    private String nom;

    @NotBlank(message = "Veuillez saisir votre prénom")
    @Column(name = "prenoms", nullable = false, length = 64)
    private String prenoms;

    @Column(nullable = false, length = 64)
    private String displayname;

    @Column(nullable = false, unique = true, length = 64)
    private String username;

    @Email(message = "Veuillez saisir une adresse e-mail correcte")
    @NotBlank(message = "Veuillez saisir une adresse e-mail")
    @Column(nullable = false, unique = true, length = 64)
    private String email;

    @NotBlank(message = "Veuillez saisir un numéro de téléphone")
    @Column(nullable = false, unique = true, length = 32)
    private String phone;

    @NotBlank(message = "Veuillez saisir un mot de passe")
    @Pattern(message = "Votre mot de passe doit comporter au moins 8 caractères dont une lettre majuscule et un chiffre.", regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")
    @Column(nullable = false, unique = true, length = 128)
    private String password;

    private Boolean active;

    private String activetoken;


    // ----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    // ----------------------------------------------------------------------
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();


}