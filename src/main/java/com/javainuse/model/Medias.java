package com.javainuse.model;

import javax.persistence.*;


@Entity
public class Medias {

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
    private Integer mediaId;

    @Column(columnDefinition = "text")
    private String med;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complain_id")
    private PublicComplain complain;

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(final Integer mediaId) {
        this.mediaId = mediaId;
    }

    public String getMed() {
        return med;
    }

    public void setMed(final String med) {
        this.med = med;
    }

    public PublicComplain getComplain() {
        return complain;
    }

    public void setComplain(final PublicComplain complain) {
        this.complain = complain;
    }

}
