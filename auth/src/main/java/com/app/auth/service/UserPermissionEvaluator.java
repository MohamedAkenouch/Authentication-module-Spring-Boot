package com.app.auth.service;

import com.app.auth.model.User;
import com.app.auth.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class UserPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        String type = targetDomainObject.getClass().getSimpleName();
        if (authentication == null || !(targetDomainObject instanceof Long)) {
            return false;
        }
        Long userId = (Long) targetDomainObject;
        String currentUsername = authentication.getName();
        User user = userRepository.findByUsername(currentUsername);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user.getId().equals(userId);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

}
