package com.salvisumedh2396.resumeapp;

import com.salvisumedh2396.resumeapp.models.User;
import com.salvisumedh2396.resumeapp.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile,Integer> {
    Optional<UserProfile> findByUserName(String userName);
}
