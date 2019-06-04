package DAO;

import Models.Role;

import java.util.List;

public interface IDAORoles {
    Role getRoleByName(String name);
    List<Role> getAllRoles();
}
