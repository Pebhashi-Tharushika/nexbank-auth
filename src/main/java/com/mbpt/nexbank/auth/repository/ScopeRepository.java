package com.mbpt.nexbank.auth.repository;

import com.mbpt.nexbank.auth.entity.ScopeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScopeRepository extends JpaRepository<ScopeEntity, Integer> {

    /**
     * Find scopes
     *
     * @param roleId role id
     * @return list of scopes
     */
    @Query(value = "select p.id, p.permission from permission p " +
            "inner join role_permission rp " +
            "on p.id = rp.permission_id " +
            "inner join role r " +
            "on rp.role_id = r.id " +
            "where rp.role_id =:roleId;", nativeQuery = true)
    public List<ScopeEntity> findScopes(@Param("roleId") Integer roleId);
}
