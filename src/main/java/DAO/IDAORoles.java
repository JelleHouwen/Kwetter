package DAO;

import Models.Role;

import java.util.List;

public interface IDAORoles {
    public Role getRoleByName(String name);
    public List<Role> getAllRoles();
}
