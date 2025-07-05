package com.mbpt.nexbank.auth.repository;

import com.mbpt.nexbank.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "select * from user where username=:username", nativeQuery = true)
    public UserEntity findUserByUserName(@Param("username") String username);
}
