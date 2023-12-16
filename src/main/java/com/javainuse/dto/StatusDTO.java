package com.javainuse.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.javainuse.model.AcceptedComplains;
import com.javainuse.model.RejectResons;




@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StatusDTO {

	private int review_statusCode;
	private AcceptedComplains accepted;
	private RejectResons rejected;
	
	
	public int getReview_statusCode() {
		return review_statusCode;
	}
	public void setReview_statusCode(int review_statusCode) {
		this.review_statusCode = review_statusCode;
	}
	public AcceptedComplains getAccepted() {
		return accepted;
	}
	public void setAccepted(AcceptedComplains accepted) {
		this.accepted = accepted;
	}
	public RejectResons getRejected() {
		return rejected;
	}
	public void setRejected(RejectResons rejected) {
		this.rejected = rejected;
	}
	
	
	
	
}
