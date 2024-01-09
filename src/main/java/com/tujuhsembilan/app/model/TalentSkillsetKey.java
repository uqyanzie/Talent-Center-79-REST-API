package com.tujuhsembilan.app.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TalentSkillsetKey implements Serializable{
    
    @Column(name = "talent_id")
    private UUID talentId;

    @Column(name = "skillset_id")
    private UUID skillsetId;
}
