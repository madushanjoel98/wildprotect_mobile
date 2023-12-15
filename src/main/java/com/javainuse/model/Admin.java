package com.javainuse.model;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//ok

@Entity
@Table(name = "admin")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Admin implements UserDetails {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @JsonIgnore
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long userId;

    @Column
    private Boolean accountNonLocked;
    
    @JsonIgnore
    @Column
    private String password;

    @Column
    private String username;
    
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adminty_id", nullable = false)
    private AdminTypes adminTypes;
    
    
    @JsonIgnore
    @ManyToMany(mappedBy = "accessforAdmins")
    private Set<Accesslevel> accessforAccesslevels=new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryid_id")
    private Countries countryid;

    @JsonIgnore
    @OneToMany(mappedBy = "admin")
    private Set<AcceptedComplains> acceptComplain=new HashSet<>();;
    
    @JsonIgnore
    @OneToMany(mappedBy = "admin")
    private Set<RejectResons> rejectComplain=new HashSet<>();;

	public Set<AcceptedComplains> getAcceptComplain() {
		return acceptComplain;
	}

	public void setAcceptComplain(Set<AcceptedComplains> acceptComplain) {
		this.acceptComplain = acceptComplain;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

   
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public Set<Accesslevel> getAccessforAccesslevels() {
        return accessforAccesslevels;
    }

    public void setAccessforAccesslevels(final Set<Accesslevel> accessforAccesslevels) {
        this.accessforAccesslevels = accessforAccesslevels;
    }

    public Countries getCountryid() {
        return countryid;
    }

    public void setCountryid(final Countries countryid) {
        this.countryid = countryid;
    }

   

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return List.of(() -> "read");
    }

    @Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public AdminTypes getAdminTypes() {
		return adminTypes;
	}

	public void setAdminTypes(AdminTypes adminTypes) {
		this.adminTypes = adminTypes;
	}

	public Set<RejectResons> getRejectComplain() {
		return rejectComplain;
	}

	public void setRejectComplain(Set<RejectResons> rejectComplain) {
		this.rejectComplain = rejectComplain;
	}

}
