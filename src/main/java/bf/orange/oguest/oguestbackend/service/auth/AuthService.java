package bf.orange.oguest.oguestbackend.service.auth;

import bf.orange.oguest.oguestbackend.dao.entity.auth.User;
import bf.orange.oguest.oguestbackend.dao.repository.auth.UserRepository;
import bf.orange.oguest.oguestbackend.payload.auth.request.LoginRequest;
import bf.orange.oguest.oguestbackend.payload.auth.response.JwtResponse;
import bf.orange.oguest.oguestbackend.security.jwt.JwtUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Data
@Service
@Slf4j
public class AuthService {

    @Autowired
    @Qualifier("ldapBean")
    AuthenticationManager ldapAuthenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    //@Autowired
    //UserService userService;
    
    @Autowired
    RoleService roleService;

    private boolean NO_ROLE = true;

    public ResponseEntity<JwtResponse> authenticateLdapUser(LoginRequest loginRequest) throws Exception {
        log.error("Authenticating LDAP user Service");
        
        Authentication auth = this.generateLdapAuthentication(loginRequest.getUsername(), loginRequest.getPassword());
        
        String jwt = this.generateToken(auth);
        System.out.println("THIS IS THE JWT "+jwt);

    		LdapUserDetailsImpl userDetails = (LdapUserDetailsImpl) auth.getPrincipal();
            System.out.println("THIS IS THE LDAP USERDETAILS "+userDetails.toString());
            log.error(userDetails.toString());
            
            User user = userRepository.findByUsername(userDetails.getUsername());

            List<String> roles = this.retrieveLdapAuthenticatedUserRoles(user);

            System.out.println("ROLES ::: "+roles);

            System.out.println("USER ::: "+user);

            return ResponseEntity.ok(new JwtResponse(jwt,
                    user.getId(),
                    user.getNom(),
                    user.getPrenoms(),
                    user.getUsername(),
                    user.getPhone(),
                    user.getEmail(),
                    roles));
    }

    /**
     * Generate authentication Object
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public Authentication generateLdapAuthentication(String username, String password) throws Exception {
        try {
            return ldapAuthenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("Oups! Votre compte est désactivé.", e);
        } catch(BadCredentialsException e) {
            throw new BadCredentialsException("BAD_CREDENTIALS_ERROR");
        }
    }

    /**
     * Get the token
     * @param auth
     * @return
     */
    public String generateToken(Authentication auth) {
        SecurityContextHolder.getContext().setAuthentication(auth);
        return jwtUtils.generateJwtToken(auth);
    }

    /**
     * Get authenticated user's roles
     * @return
     */
    public List<String> retrieveLdapAuthenticatedUserRoles(User user){
        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(role -> {
            roles.add(role.getSlug());
        });
        System.out.println("THIS IS THE AUTH LDAP USER ROLES"+roles);
        return roles;
    }

    /*
     * Authenticating the new user
     */
    /*public ResponseEntity authenticateRegisteredUser(String email, String password) {

        Authentication auth = generateAuthentication(email, password);
        String jwt = generateToken(auth);
        UserDetailsImpl userDetails = retrieveAuthenticatedUser(auth);
        List<String> authRoles = retrieveAuthenticatedUserRoles(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getNom(),
                userDetails.getPrenoms(),
                userDetails.getPhone(),
                userDetails.getEmail(),
                authRoles));
    }*/


	public Boolean passwordRegExValidator(String password) throws PatternSyntaxException {
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");
        log.error("PATTERN "+pattern);
        Matcher matcher = pattern.matcher(password);
        Boolean bo = matcher.find();
        log.error("PATTERN RETURN "+bo);
        return bo;		
	}
	
	public Boolean passwordMatching(String password, String confirmPassword) {
		return confirmPassword.contentEquals(password);
	}
	
	
	public static String encodeString(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
	}
	
	public static String decodeString(String encodedStr) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedStr);
        return new String(decodedBytes);
	}
	
	
	public static String generateRandomString() {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
          +"lmnopqrstuvwxyz";
		Random rnd = new Random();
		int len = 64;
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}


}
