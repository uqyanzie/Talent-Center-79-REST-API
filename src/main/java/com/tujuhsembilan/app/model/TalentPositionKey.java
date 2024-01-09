package com.tujuhsembilan.app.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TalentPositionKey implements Serializable{
    
    @Column(name = "talent_id")
    private UUID talentId;

    @Column(name = "position_id")
    private UUID positionId;

}
