package com.mbpt.nexbank.auth.repository;

import com.mbpt.nexbank.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * Find user by username
     *
     * @param username username
     * @return user entity
     */
    @Query(value = "select * from users where username=:username", nativeQuery = true)
    public UserEntity findUserByUserName(@Param("username") String username);


    /**
     * Find scopes
     *
     * @param userId user id
     * @return list of scopes
     */
    @Query(value = "select p.id, p.permission from permission p " +
            "inner join role_permission rp on p.id = rp.permission_id " +
            "inner join role r on rp.role_id = r.id " +
            "inner join users u on u.role_id = r.id " +
            "where u.id = :userId", nativeQuery = true)
    public String findScopes(@Param("userId") int userId);
}
