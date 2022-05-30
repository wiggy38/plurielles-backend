package bf.orange.oguest.oguestbackend.auth.service;

import bf.orange.oguest.oguestbackend.auth.entity.Role;
import bf.orange.oguest.oguestbackend.auth.entity.User;
import bf.orange.oguest.oguestbackend.auth.payload.request.LdapSignupRequest;
import bf.orange.oguest.oguestbackend.auth.repository.UserRepository;
import bf.orange.oguest.oguestbackend.auth.payload.request.LoginRequest;
import bf.orange.oguest.oguestbackend.auth.payload.response.JwtResponse;
import bf.orange.oguest.oguestbackend.auth.security.jwt.JwtUtils;
import bf.orange.oguest.oguestbackend.utils.AppConstants;
import bf.orange.oguest.oguestbackend.utils.ExceptionMsg;
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
    @Autowired
    RoleService roleService;

    private boolean NO_ROLE = true;


    /**
     * Register Ldap users
     * @param signUpRequest
     * @return
     * @throws
     */
    public User registerLdapUser(LdapSignupRequest signUpRequest) throws Exception {

        log.error("Creating Ldap user from SignupRequest > "+signUpRequest.getEmail());

        // Check is cuid already exist
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new Exception(ExceptionMsg.USERNAME_EXIST_ERROR);
        }

        // Create new user's account
        User user = new User();
        user.setNom(signUpRequest.getNom());
        user.setPrenoms(signUpRequest.getPrenoms());
        user.setDisplayname(user.getPrenoms()+" "+user.getNom());
        user.setUsername(signUpRequest.getUsername());
        user.setPhone(signUpRequest.getPhone());
        user.setEmail(signUpRequest.getEmail());
        user.setActive(true);
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        //encoder.encode(AppConstants.DEFAULT_PASSWORD), true, null);
        //log.error("LDAP USER requested Roles : "+ user.toString());

        if(signUpRequest.getRole()!=null) {
            // Retrieve and attache roles
            List<Role> roles = this.processRoles(signUpRequest.getRole(), true);
            user.setRoles(roles);
        }else{
            user.setRoles(new ArrayList<>());
        }
        log.error("LDAP USER Computed Roles : "+ user.getActive()+ " > "+user.getActivetoken()+ " > "+user.getDisplayname()+ " > "+user.getEmail()+ " > "+user.getNom()+ " > "+user.getPassword()+ " > "+user.getPhone()+ " > "+user.getPrenoms()+ " > "+user.getUsername());

        // Send auth credentials to registered user
        /*try {
            mailSender.sendHtmlEmail(savedUser, activationCode);
        } catch (MessagingException e) {
            //e.printStackTrace();
            throw new MessagingException("Echec authentification au serveur mail");
        }*/

        return userRepository.save(user);

    }

    /*
     * Compute roles for user upon registration
     */
    public List<Role> processRoles(List<String> strRoles, Boolean flag) {

        List<Role> roles = new ArrayList<>();

        strRoles.forEach(role -> {
                Role adminRole = roleService.findBySlug(role);
                log.error("GETTING ADMIN ROLE ::: " + adminRole.getNom());
                roles.add(adminRole);
        });

        return roles;
    }

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
