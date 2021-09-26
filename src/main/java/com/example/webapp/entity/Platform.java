package com.example.webapp.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "platforms")
@EqualsAndHashCode(of = {"name"})
@ToString(of = {"name"})
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    private Integer id;
    private String name;
    @ManyToMany
    @JoinTable(name = "ads_platforms", joinColumns = @JoinColumn(name = "platform_id"),
            inverseJoinColumns = @JoinColumn(name = "ad_id"))
    @Column(name = "ad")
    private List<Ad> ads;

    public Platform(int id, List<Ad> ads, String name) {
        this.id = id;
        this.ads = ads;
        this.name = name;
    }

    //    @Override
//    public boolean equals(Object obj) {
//        if (obj == this)
//            return true;
//        else
//            return false;
//    }
    public Platform() {
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
