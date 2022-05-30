package bf.orange.oguest.oguestbackend.auth.security.services;

import bf.orange.oguest.oguestbackend.auth.entity.User;
import bf.orange.oguest.oguestbackend.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
	UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	try {
    		User user = userRepository.findByUsername(username);
    		if(null != user) {
    			System.out.println("loadUserByUsername "+user.getActive());
    			UserDetailsImpl userDetailImpl = UserDetailsImpl.build(user);
    			System.out.println("loadUserByUsername::findByUsername:UserDetailsImpl "+userDetailImpl.getUsername());
    			/*if(!userDetailImpl.isEnabled()) {
    				throw new DisabledException("Oups! User is disabled");
    			}*/
    			return userDetailImpl;
    		}
    		else {
    			throw new UsernameNotFoundException("User Null : User Not Found with username: " + username);
    		}
    	}
    	catch (UsernameNotFoundException e) {
    		throw new UsernameNotFoundException("User Not Found with username: " + username);
    	}
     
    }

}
