package com.javainuse.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity

public class Accesslevel {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer accessid;

    @Column(length = 45)
    private String accesslevel;

    @ManyToMany
    @JoinTable(
            name = "Accessfor",
            joinColumns = @JoinColumn(name = "accessid"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private Set<Admin> accessforAdmins;

    public Integer getAccessid() {
        return accessid;
    }

    public void setAccessid(final Integer accessid) {
        this.accessid = accessid;
    }

    public String getAccesslevel() {
        return accesslevel;
    }

    public void setAccesslevel(final String accesslevel) {
        this.accesslevel = accesslevel;
    }

    public Set<Admin> getAccessforAdmins() {
        return accessforAdmins;
    }

    public void setAccessforAdmins(final Set<Admin> accessforAdmins) {
        this.accessforAdmins = accessforAdmins;
    }

}
