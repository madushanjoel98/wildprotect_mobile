package com.javainuse.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PublicComplain {

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
    private Long pcompId;

    @Column(nullable = false, length = 90)
    @NotEmpty(message = "title can't be empty")
    private String title;
   
    @Column(nullable = false, length = 100)
    @NotEmpty(message = "location Details can't be empty")
    private String locationDetails;

    @Column(nullable = false, length = 900)
    @NotEmpty(message = "Complaint can't be empty")
    private String complain;

    
    @Column(nullable = true)
    @Min(0)
    @Max(2)
    private int review_status=0;
    @JsonIgnore
    @OneToMany(mappedBy = "complain")
    private Set<AcceptedComplains> complainAcceptedComplainses=new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "complain")
    private Set<Medias> complainMediases =new HashSet<>();;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicid_id", nullable = false)
    private PublicLogin publicid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Countries countries;
    
    @JsonIgnore
    @OneToMany(mappedBy = "complaintid")
    private Set<RejectResons> complaintidRejectResonses=new HashSet<>();

    @Column(nullable = false)
    private Date complaintDate;
    
    public int getReview_status() {
		return review_status;
	}

	public void setReview_status(int review_status) {
		this.review_status = review_status;
	}

	public Countries getCountries() {
		return countries;
	}

	public void setCountries(Countries countries) {
		this.countries = countries;
	}

	public Long getPcompId() {
        return pcompId;
    }

    public void setPcompId(final Long pcompId) {
        this.pcompId = pcompId;
    }

 
   

    public String getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(final String locationDetails) {
        this.locationDetails = locationDetails;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(final String complain) {
        this.complain = complain;
    }

    public Set<AcceptedComplains> getComplainAcceptedComplainses() {
        return complainAcceptedComplainses;
    }

    public void setComplainAcceptedComplainses(
            final Set<AcceptedComplains> complainAcceptedComplainses) {
        this.complainAcceptedComplainses = complainAcceptedComplainses;
    }

    public Set<Medias> getComplainMediases() {
        return complainMediases;
    }

    public void setComplainMediases(final Set<Medias> complainMediases) {
        this.complainMediases = complainMediases;
    }

    public PublicLogin getPublicid() {
        return publicid;
    }

    public void setPublicid(final PublicLogin publicid) {
        this.publicid = publicid;
    }

    public Set<RejectResons> getComplaintidRejectResonses() {
        return complaintidRejectResonses;
    }

    public void setComplaintidRejectResonses(final Set<RejectResons> complaintidRejectResonses) {
        this.complaintidRejectResonses = complaintidRejectResonses;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}

}

