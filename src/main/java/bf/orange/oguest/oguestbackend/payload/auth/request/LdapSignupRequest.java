package bf.orange.oguest.oguestbackend.payload.auth.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class LdapSignupRequest {

    @Size(min = 3, max = 32, message = "")
    @NotBlank(message = "Veuillez saisir votre nom")
    @Column(name = "nom", nullable = false, length = 24)
    private String nom;

    @NotBlank(message = "Veuillez saisir votre prénom")
    @Size(min = 3, max = 40)
    private String prenoms;

    @Column(length = 32)
    private String phone;

    @NotBlank(message = "Le nom d'utilisateur ne peut être vide.")
    @Size(min = 3, max = 40)
    private String username;

    @Size(max = 50)
    private String email;

    @NotBlank(message = "Veuillez saisir un mot de passe")
    @Pattern(message ="Votre mot de passe doit comporter au moins 8 caractères dont une lettre majuscule et un chiffre.", regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")
    @Column(nullable = false, unique = true, length = 128)
    private String password;

    private List<String> role;

}
