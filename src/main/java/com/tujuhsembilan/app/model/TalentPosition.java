package com.tujuhsembilan.app.model;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
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
public class TalentPosition {

    @EmbeddedId
    private TalentPositionKey talentPositionId;

    @ManyToOne
    @MapsId("talentId")
    @JoinColumn(name = "talent_id")
    private Talent talent;
    
    @ManyToOne
    @MapsId("positionId")
    @JoinColumn(name = "position_id")
    private Position position;    

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
