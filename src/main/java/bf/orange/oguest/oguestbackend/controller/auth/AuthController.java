package bf.orange.oguest.oguestbackend.controller.auth;

import bf.orange.oguest.oguestbackend.dao.repository.auth.RoleRepository;
import bf.orange.oguest.oguestbackend.dao.repository.auth.UserRepository;
import bf.orange.oguest.oguestbackend.payload.auth.request.LoginRequest;
import bf.orange.oguest.oguestbackend.payload.auth.response.JwtResponse;
import bf.orange.oguest.oguestbackend.security.jwt.JwtUtils;
import bf.orange.oguest.oguestbackend.service.auth.AuthService;
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
@RequestMapping("/api/auth")
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

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        for (Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
        //return new HashMap<>(claims);
    }

}