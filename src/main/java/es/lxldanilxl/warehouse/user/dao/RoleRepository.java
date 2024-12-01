package es.lxldanilxl.warehouse.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.lxldanilxl.warehouse.user.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
