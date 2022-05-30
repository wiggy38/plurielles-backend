package bf.orange.oguest.oguestbackend.service;

import bf.orange.oguest.oguestbackend.dao.entity.Role;
import bf.orange.oguest.oguestbackend.dao.repository.RoleRepository;
import bf.orange.oguest.oguestbackend.utils.ExceptionMsg;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Data
@Service
@Slf4j
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        log.error("RoleService: Get ROLES LIST -----------------" + roleRepository.findAll());
        return roleRepository.findAll();
    }

    public Role save(Role role) {
    	return roleRepository.save(role);
    }
    /*public ResponseEntity< Role > getRoleById(@PathVariable(value = "id") Long roleId)
            throws ResourceNotFoundException {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Aucun role trouvé pour cet Id :: " + roleId));
        return ResponseEntity.ok().body(role);
    }*/

    public Role findRoleById(@PathVariable(value = "id") Long roleId)
            throws ResourceNotFoundException {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Aucun role trouvé pour cet Id :: " + roleId));
        log.error("RoleService: Get SINGLE Role -----------------" + role.toString());
        return role;
    }

    public Role updateRole(Long roleId, Role roleDetails) {
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if (roleOptional.isPresent()) {
            //String currentPassword = oldRole.getPassword();
            roleOptional.get().setNom(roleDetails.getNom());
            roleOptional.get().setSlug(roleDetails.getSlug());
            roleOptional.get().setDefaut(roleDetails.getDefaut());
            return roleRepository.save(roleOptional.get());
        } else {
            throw new ResourceNotFoundException(ExceptionMsg.RESOURCENOTFOUND_MSG);
        }

    }

    /*public Role findRoleBySlug(String slug) {
        return roleRepository.findBySlug(slug);
    }

    public Role findAdminRole() {
        return roleRepository.findBySlug(AppConstants.ADMIN_ROLE);
    }*/

    public void createDefaultRoles(){
        Role adminRole = new Role();
        adminRole.setNom("Administrateur");
        adminRole.setSlug("admin");
        adminRole.setDefaut(false);
        roleRepository.save(adminRole);

        Role candidatRole = new Role();
        candidatRole.setNom("Candidat");
        candidatRole.setSlug("candidat");
        candidatRole.setDefaut(true);
        roleRepository.save(candidatRole);

        Role userRole = new Role();
        userRole.setNom("Utilisateur");
        userRole.setSlug("user");
        userRole.setDefaut(false);
        roleRepository.save(userRole);
    }

}
