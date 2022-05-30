package bf.orange.oguest.oguestbackend.auth.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    // External user
    private Long id;
    private String nom;
    private String prenoms;
    private String phone;
    private String email;
    private List<String> roles;
    // Ldap user
    private String username;

    public JwtResponse(String accessToken, Long id, String nom, String prenoms, String username, String phone, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.roles = roles;
    }

    public JwtResponse(String accessToken, String cuid) {
        this.token = accessToken;
        this.username = cuid;
	}

	public JwtResponse(String accessToken, Long id, String username, String displayname,
			String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
	}

}
