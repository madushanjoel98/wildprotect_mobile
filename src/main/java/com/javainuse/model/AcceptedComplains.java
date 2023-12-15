package com.javainuse.model;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class AcceptedComplains {

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


    private Long idAccepts;

    @Column(nullable = false, length = 45)
    private String acceptReason;

    @Column(nullable = false, length = 45)
    private String action;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complain_id", nullable = false)
    private PublicComplain complain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;
    
    @Column(nullable = false)
    private Date accepttDate;
    

    public Date getComplaintDate() {
		return accepttDate;
	}

	public void setComplaintDate(Date complaintDate) {
		this.accepttDate = complaintDate;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Long getIdAccepts() {
        return idAccepts;
    }

    public void setIdAccepts(final Long idAccepts) {
        this.idAccepts = idAccepts;
    }

    public String getAcceptReason() {
        return acceptReason;
    }

    public void setAcceptReason(final String acceptReason) {
        this.acceptReason = acceptReason;
    }

    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public PublicComplain getComplain() {
        return complain;
    }

    public void setComplain(final PublicComplain complain) {
        this.complain = complain;
    }

}
