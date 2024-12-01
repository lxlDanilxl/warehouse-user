package es.lxldanilxl.warehouse.user.service;

import es.lxldanilxl.warehouse.user.model.Role;

public interface RoleService {

    /**
     * Creates a new role.
     *
     * @param role The role to be created. The ID of the role will be ignored.
     * @return The created role.
     */
    Role createRole(Role role);

    /**
     * Retrieves a role by its ID.
     *
     * @param id The ID of the role to retrieve.
     * @return The role with the specified ID.
     * @throws NoSuchElementException If no role is found with the given ID.
     */
    Role getRoleById(Long id);

    /**
     * Updates an existing role.
     *
     * @param role The role to update. The role must have a valid ID.
     * @return The updated role.
     */
    Role updateRole(Role role);

    /**
     * Deletes a role by its ID.
     *
     * @param id The ID of the role to delete.
     * @throws NoSuchElementException If no role is found with the given ID.
     */
    void deleteRole(Long id);

    /**
     * Retrieves all roles.
     *
     * @return An iterable collection of all roles.
     */
    Iterable<Role> getAllRoles();

}