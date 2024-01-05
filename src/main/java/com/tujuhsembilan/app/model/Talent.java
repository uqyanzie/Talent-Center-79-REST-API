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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "talent")
public class Talent {
    
    @Id
    private UUID talentId;

    @ManyToOne
    @JoinColumn(name = "talent_level_id")
    private TalentLevel talentLevel;

    @ManyToOne
    @JoinColumn(name = "talent_status_id")
    private TalentStatus talentStatus;

    @ManyToOne
    @JoinColumn(name = "employee_status_id")
    private EmployeeStatus employeeStatus;

    @OneToMany(mappedBy = "talent")
    Set<TalentSkillset> talentSkillsets;

    @OneToMany(mappedBy = "talent")
    Set<TalentPosition> talentPositions;

    private String talentName;

    private String talentPhotoFilename;

    private String employeeNumber;

    private Character gender;

    @Column(name = "birth_date")
    private Timestamp birthDate;

    private String talentDescription;

    private String talentCvFilename;

    private Integer experience;

    private String email;

    @Column(name = "cellphone")
    private String cellphone;

    private String biographyVideoUrl;

    private Boolean isAddToListEnable;

    private Boolean talentAvailability;

    private Boolean isActive;

    private Integer totalProjectCompleted;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "created_time")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdTime;

    @Column(name = "last_modified_time")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp lastModifiedTime;

}
