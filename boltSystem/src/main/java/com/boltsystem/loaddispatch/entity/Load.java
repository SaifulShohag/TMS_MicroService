package com.boltsystem.loaddispatch.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "loads")
public class Load {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    @Enumerated(EnumType.STRING)
    private LoadStatus status = LoadStatus.PENDING;
    private Long assignedDriverID;

    public Load() {}
    public Load(String description) {
        this.description = description;
    }

    public Long getID() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public LoadStatus getStatus() {
        return status;
    }

    public void setStatus( LoadStatus status ) {
        this.status = status;
    }

    public Long getAssignedDriverId() {
        return assignedDriverID;
    }

    public void setAssignedDriverId( Long assignedDriverId ) {
        this.assignedDriverID = assignedDriverId;
    }

}