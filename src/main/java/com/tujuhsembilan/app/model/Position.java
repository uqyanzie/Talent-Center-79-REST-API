package com.tujuhsembilan.app.model;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "position")
public class Position {
    
    @Id
    @Column(name = "position_id")
    private UUID positionId;

    @Column(name = "position_name")
    private String positionName;

    @Column(name = "is_active")
    private boolean isActive;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;


    @CreatedDate
    @Column(name = "created_time")
    private Timestamp createdTime;

    @LastModifiedDate
    @Column(name = "last_modified_time")
    private Timestamp lastModifiedTime;
}
