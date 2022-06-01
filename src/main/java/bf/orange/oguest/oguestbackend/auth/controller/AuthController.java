package bf.orange.oguest.oguestbackend.auth.controller;

import bf.orange.oguest.oguestbackend.auth.entity.User;
import bf.orange.oguest.oguestbackend.auth.payload.request.LdapSignupRequest;
import bf.orange.oguest.oguestbackend.auth.repository.RoleRepository;
import bf.orange.oguest.oguestbackend.auth.repository.UserRepository;
import bf.orange.oguest.oguestbackend.auth.payload.request.LoginRequest;
import bf.orange.oguest.oguestbackend.auth.payload.response.JwtResponse;
import bf.orange.oguest.oguestbackend.auth.security.jwt.JwtUtils;
import bf.orange.oguest.oguestbackend.auth.service.AuthService;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/backend/api/auth")
@Slf4j
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    
    @Autowired
    AuthService authService;

    private final boolean NO_ROLE = true;

    /**
     * LDAP Login
     * @param loginRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/ldap/users/signin")
    public ResponseEntity<JwtResponse> authenticateLdapUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        log.error("Authenticating LDAP user");
        
		try {
			return authService.authenticateLdapUser(loginRequest);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BAD_CREDENTIALS_ERROR__");
		}
        
    }


    /**
     * Register Ldap users
     * @param signUpRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/ldap/signup/user")
    public ResponseEntity<?> registerLdapUser(@RequestBody @Valid LdapSignupRequest signUpRequest) throws Exception {

        log.error("Creating ldap user from SignUp request");
        log.error(signUpRequest.getEmail());

        if(signUpRequest.getEmail()==null) {
            System.out.println("signUpRequest.getEmail() is Null. Setting the default one.");
            signUpRequest.setEmail(signUpRequest.getUsername()+"@adobf.orangebf");
        }

        if(signUpRequest.getPhone()==null) {
            System.out.println("signUpRequest.getPhone() is Null. Setting the default one.");
            signUpRequest.setPhone("7777"+signUpRequest.getUsername().substring(4));
        }

        User user = authService.registerLdapUser(signUpRequest);
        //HashMap<String, String> response = new HashMap<>();
        //if(user!=null) {
        //response.put("status", "Success");
        //response.put("message", AppConstants.SUCCESSFUL_REGISTRATION);
        //return authenticateRegisteredUser(signUpRequest.getEmail(), signUpRequest.getPassword());
        return ResponseEntity.status(200).body(true);
        //}

    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        for (Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
        //return new HashMap<>(claims);
    }

}