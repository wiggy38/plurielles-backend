package bf.orange.oguest.oguestbackend.auth.security.jwt;


import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${oguest.app.security.jwtSecret}")
    private String jwtSecret;

    @Value("${oguest.app.security.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        System.out.println("::>>>>>>>>>>> Inside generateJwtToken()");
    	
    	System.out.println("GENERATING TOKEN "+authentication.toString());
    

        /*if(authentication.getPrincipal() instanceof LdapUserDetailsImpl) {
        	
        	System.out.println("LDAP USER DETAIL INSTANCE");
        	System.out.println(authentication.getPrincipal().getClass());
        	
        	LdapUserDetailsImpl userPrincipal = (LdapUserDetailsImpl) authentication.getPrincipal();
        	
        	return this.getToken(userPrincipal.getUsername());
        	
        }else {*/
        	
        	System.out.println("USER DETAIL INSTANCE");
        	
        	LdapUserDetailsImpl userPrincipal = (LdapUserDetailsImpl) authentication.getPrincipal();
        	
        	return this.getToken(userPrincipal.getUsername());
        	
        /*}*/
    }

    private String getToken(String username) {
        System.out.println("::>>>>>>>>>>> Inside getToken()");
    	return Jwts.builder()
                .setSubject((username))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
	}

	public String getUserNameFromJwtToken(String token) {
        System.out.println("::>>>>>>>>>>> Inside getUserNameFromJwtToken()");
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        System.out.println("::>>>>>>>>>>> Inside validateJwtToken()");
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
