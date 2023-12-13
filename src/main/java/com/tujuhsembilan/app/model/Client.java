package com.tujuhsembilan.app.model;

import java.sql.Timestamp;
import java.util.UUID;

import org.checkerframework.checker.units.qual.C;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"client\"")
public class Client {
    
    @Id
    @Column(name = "client_id")
    public UUID clientId;
    
    @Column(name = "client_position_id")
    public UUID clientPositionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    public String clientName;

    public String gender;

    @Column(name = "birth_date")
    public Timestamp birthDate;

    public String email;

    @Column(name = "agency_name")
    public String agencyName;

    @Column(name = "agency_address")
    public String agencyAddress;

    @Column(name = "is_active")
    public Boolean isActive;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdTime;

    @Column
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp lastModifiedTime;
}
