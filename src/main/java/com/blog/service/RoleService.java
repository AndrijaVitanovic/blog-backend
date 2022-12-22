package com.blog.service;

import com.blog.entity.Role;

public interface RoleService {

    /**
     * Finds a role in database by name.
     * @param name - Name of the role we are looking for.
     * @return - Role by that name.
     */
    Role findByName(String name);

}
