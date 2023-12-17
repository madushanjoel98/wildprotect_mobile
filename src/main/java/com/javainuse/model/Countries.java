package com.javainuse.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import java.util.HashSet;
import java.util.Set;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Countries {

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
    private Integer id;

    @Column(nullable = false, length = 2)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "countryid",fetch = FetchType.LAZY)
    private Set<Admin> countryidAdmins =new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "countryid",fetch = FetchType.LAZY)
    private Set<PublicLogin> countryidPublicLogins =new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "pcompId",fetch = FetchType.LAZY)
    private Set<PublicComplain> publicComplain =new HashSet<>();;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Admin> getCountryidAdmins() {
        return countryidAdmins;
    }

    public void setCountryidAdmins(final Set<Admin> countryidAdmins) {
        this.countryidAdmins = countryidAdmins;
    }

    public Set<PublicLogin> getCountryidPublicLogins() {
        return countryidPublicLogins;
    }

    public void setCountryidPublicLogins(final Set<PublicLogin> countryidPublicLogins) {
        this.countryidPublicLogins = countryidPublicLogins;
    }

}
