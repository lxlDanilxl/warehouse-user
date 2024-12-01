package es.lxldanilxl.warehouse.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import es.lxldanilxl.warehouse.user.model.Role;
import es.lxldanilxl.warehouse.user.service.RoleService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * Create a new role.
     * Only accessible by users in the admin group.
     * 
     * @param role the role to be created
     * @return the created role
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role createdRole = roleService.createRole(role);
        return ResponseEntity.ok(createdRole);
    }

    /**
     * Get all roles.
     * Only accessible by users in the admin group.
     * 
     * @return a list of roles
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {

        List<Role> roles = new ArrayList<>();
        roleService.getAllRoles().forEach(roles::add);
        return ResponseEntity.ok(roles);
    }

    /**
     * Get a role by its ID.
     * Only accessible by users in the admin group.
     *
     * @param id the ID of the role
     * @return the role with the specified ID
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Role role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    /**
     * Update a role.
     * Only accessible by users in the admin group.
     * 
     * @param id   the ID of the role to update
     * @param role the updated role
     * @return the updated role
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
        Role updatedRole = roleService.updateRole(role);
        return ResponseEntity.ok(updatedRole);
    }

    /**
     * Delete a role.
     * Only accessible by users in the admin group.
     * 
     * @param id the ID of the role to delete
     * @return a response indicating the role was deleted
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
