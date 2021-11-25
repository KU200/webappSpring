package com.example.webapp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ads")
@EqualsAndHashCode(of = {"name"})
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
//    @Enumerated(EnumType.STRING)
    private Status status;
    private String assetUrl;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;
    @ManyToMany
    @JoinTable(name = "ads_platforms", joinColumns = @JoinColumn(name = "ad_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id", nullable = true))
    @Column(name = "platform")
    private List<Platform> platforms;

    public Ad() {
    }
    public Ad(List<Platform> platforms) {
        this.platforms=platforms;
    }
//    String platformsToString(Set<Platform> platforms){
//        StringBuilder str = new StringBuilder();
//        for (Platform platform:platforms) {
//            str.append(platform.getName()+" ");
//        }
//        return str.toString();
//    }
//    String stringPlatforms = platformsToString(platforms);

    public Ad(String name, String assetUrl, Campaign campaign, List<Platform> platforms) {
        this.name = name;
        this.status = campaign.getStatus();
        this.assetUrl = assetUrl;
        this.campaign = campaign;
        this.platforms = platforms;
    }

    public Ad(String name, String assetUrl, Campaign campaign) {
        this.name = name;
        this.status = campaign.getStatus();
        this.assetUrl = assetUrl;
        this.campaign = campaign;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
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