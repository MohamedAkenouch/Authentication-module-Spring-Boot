package com.app.auth.repo;

import com.app.auth.model.*;
import org.springframework.data.jpa.repository.*;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
