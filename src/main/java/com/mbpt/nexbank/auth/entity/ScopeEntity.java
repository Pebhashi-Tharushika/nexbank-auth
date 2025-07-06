package com.mbpt.nexbank.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permission")
@Entity
public class ScopeEntity {
    @Id
    @Column(name = "permission_id")
    private Integer permissionId;

    @Column(name = "permission")
    private String permission;

}
