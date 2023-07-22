package com.cristian.ec.explorecali.repo;

import com.cristian.ec.explorecali.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findRoleByRoleName(String name);
}
