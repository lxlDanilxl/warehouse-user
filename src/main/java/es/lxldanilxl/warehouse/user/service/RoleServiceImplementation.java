package es.lxldanilxl.warehouse.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.lxldanilxl.warehouse.user.model.Role;
import es.lxldanilxl.warehouse.user.dao.RoleRepository;

@Service
public class RoleServiceImplementation implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Creates a new role.
     *
     * @param role The role to be created. The ID of the role will be ignored.
     * @return The created role.
     */
    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    /**
     * Retrieves a role by its ID.
     *
     * @param id The ID of the role to retrieve.
     * @return The role with the specified ID.
     * @throws NoSuchElementException If no role is found with the given ID.
     */
    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow();
    }

    /**
     * Updates an existing role.
     *
     * @param role The role to update. The role must have a valid ID.
     * @return The updated role.
     */
    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    /**
     * Deletes a role by its ID.
     *
     * @param id The ID of the role to delete.
     * @throws NoSuchElementException If no role is found with the given ID.
     */
    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    /**
     * Retrieves all roles.
     *
     * @return An iterable collection of all roles.
     */
    @Override
    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
