package bf.orange.oguest.oguestbackend.auth.controller;

import bf.orange.oguest.oguestbackend.auth.entity.User;
import bf.orange.oguest.oguestbackend.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/backend/api/user")
@Slf4j
public class userController {

    @Autowired
    UserService userService;

    @GetMapping("user")
    public String helloUser() {
        return "Hello User";
    }

    @GetMapping("admin")
    public String helloAdmin() {
        return "Hello Admin";
    }

    /**
     * Rechercher un utilisateur de Ldap avec son cuid
     *
     * @param cuid CUID de l'employé
     * @return ResponseEntity<ArrayList<User>>
     * @throws Exception ResourceNotFoundException
     */
    @GetMapping("/ldap/search/cuid/{cuid}")
    @ResponseBody
    public ResponseEntity<ArrayList<User>> searchUserByCuid(@PathVariable(value = "cuid") @Valid String cuid)
            throws Exception {
        System.out.println(cuid);
        User user = userService.searchByCuid(cuid);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        System.out.println("USERRRRR " + users);

        return ResponseEntity.ok().body(users);
        // return ResponseEntity.ok().body(user);
    }

    @Secured({"SUPERADMIN"})
    @GetMapping("/list/all")
    @ResponseBody
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

}
