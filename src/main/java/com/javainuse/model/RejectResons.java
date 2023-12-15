package com.javainuse.model;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RejectResons {

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
    private Long idrejectResons;

    @Column(nullable = false, columnDefinition = "text",length = 1500)
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complaintid_id", nullable = false)
    private PublicComplain complaintid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;
    
    @Column(nullable = false)
    private Date rejectDate;

    public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Date getRejectDate() {
		return rejectDate;
	}

	public void setRejectDate(Date rejectDate) {
		this.rejectDate = rejectDate;
	}

	public Long getIdrejectResons() {
        return idrejectResons;
    }

    public void setIdrejectResons(final Long idrejectResons) {
        this.idrejectResons = idrejectResons;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(final String reason) {
        this.reason = reason;
    }

    public PublicComplain getComplaintid() {
        return complaintid;
    }

    public void setComplaintid(final PublicComplain complaintid) {
        this.complaintid = complaintid;
    }

}
