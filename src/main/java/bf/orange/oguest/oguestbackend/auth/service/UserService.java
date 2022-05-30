package bf.orange.oguest.oguestbackend.auth.service;

import bf.orange.oguest.oguestbackend.auth.entity.User;
import bf.orange.oguest.oguestbackend.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.Arrays;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
@Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    LdapTemplate ldapTemplate;

    public List<User> findAll() {
        return userRepository.findAll();
    }


    /**
     *  Search un utilisateur par son cuid de l'ad
     * @param cuid
     * @return User
     * @throws Exception
     */
    public User searchByCuid(String cuid) throws Exception{
        LdapQuery query = query().base("").countLimit(1).where("sAMAccountName").is(cuid)
                .and("objectclass").is("person").and("sn").isPresent();
        System.out.println(Arrays.toString(query.attributes()));
        try {
            List<User> list = ldapTemplate.search(query, this::mapper);
            return list.size() == 0 ? null : list.get(0);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    private User mapper(Attributes attrs) throws NamingException {

        User user = new User();

        System.out.println(attrs.toString());

        if (attrs.get("sAMAccountName") != null){
            user.setUsername(attrs.get("sAMAccountName").get().toString());
        }
        if (attrs.get("givenName") != null){
            user.setPrenoms(attrs.get("givenName").get().toString());
        }

        if (attrs.get("sn") != null){
            user.setNom(attrs.get("sn").get().toString());
        }

        if (attrs.get("mail") != null){
            user.setEmail(attrs.get("mail").get().toString());
        }
        else {
            user.setEmail(attrs.get("sAMAccountName").get().toString()+"@adobf.orangebf");
        }

        //if (attrs.get("telephonenumber") != null){
        //    user.setMsisdn(attrs.get("telephonenumber").get().toString());
        //}

        if (attrs.get("displayname") != null){
            user.setDisplayname(attrs.get("displayname").get().toString());
        }

        //log.error(attrs.get("distinguishedname").get().toString());

        return user;
    }

}
