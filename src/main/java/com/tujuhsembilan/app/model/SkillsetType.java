package com.tujuhsembilan.app.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "skillset_type")
public class SkillsetType {

    @Id
    @Column(name = "skillset_type_id")
    private UUID skillsetTypeId;

    @OneToMany(mappedBy = "skillsetType")
    private Set<Skillset> skillsets; 

    @Column(name = "skillset_type_name")
    private String skillsetTypeName;

    @Column(name = "is_programming_skill")
    private boolean isProgrammingSkill;

    @Column(name = "is_active")
    private boolean isActive;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time")
    private Timestamp createdTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_time")
    private Timestamp lastModifiedTime;
}
