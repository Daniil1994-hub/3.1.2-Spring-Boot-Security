// RoleService.java
package ru.kata.spring.boot_security.demo.servicec;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();
    Set<Role> getRolesByIds(List<Long> roleIds);
    Role findByName(String name);
}