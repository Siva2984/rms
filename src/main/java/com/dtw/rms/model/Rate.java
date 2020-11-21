package com.dtw.rms.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

/** Represents an RATE model class
 * @author Sivakumar Panchu
 * @version 1.0
 * @since 21/09/2020
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rate {
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rateid")
	private Long rateId;

	public String getRateDescription() {
		return rateDescription;
	}

	public void setRateDescription(String rateDescription) {
		this.rateDescription = rateDescription;
	}

	public Date getRateExpirationDate() {
		return rateExpirationDate;
	}

	public void setRateExpirationDate(Date rateExpirationDate) {
		this.rateExpirationDate = rateExpirationDate;
	}

	public Date getRateEffectiveDate() {
		return rateEffectiveDate;
	}

	public void setRateEffectiveDate(Date rateEffectiveDate) {
		this.rateEffectiveDate = rateEffectiveDate;
	}
	@Column(name="ratedescription")
	private String rateDescription;
	@Column(name="rateexpirationdate")
	private Date rateExpirationDate;
	@Column(name="rateeffectivedate")
	private Date rateEffectiveDate;

	public Long getRateId() {
		return rateId;
	}

	public void setRateId(Long rateId) {
		this.rateId = rateId;
	}

	private Integer amount;

}
