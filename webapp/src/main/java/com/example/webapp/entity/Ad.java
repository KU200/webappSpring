package com.example.webapp.entity;

import javax.persistence.*;

import static com.example.webapp.entity.Status.Planned;

@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Status status;
    private String assetUrl;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="campaignId")
    private Campaign campaign;

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Ad(String name, String assetUrl) {
        this.name = name;
        this.status = Planned;
        this.assetUrl = assetUrl;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAssetUrl(String assetUrl) {
        this.assetUrl = assetUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public String getAssetUrl() {
        return assetUrl;
    }

}
