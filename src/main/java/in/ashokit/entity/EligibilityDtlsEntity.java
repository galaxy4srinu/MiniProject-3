package in.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "ELIGIBILITY_DTLS")
public class EligibilityDtlsEntity {

	@Id
	@Column(name = "ELIG_ID")
	@GeneratedValue
	private Integer eligId;

	@Column(name = "PLAN_NAME")
	private String planName;

	@Column(name = "PLAN_STATUS")
	private String planStatus;

	@Column(name = "BENEFIT_AMT")
	private Double benefitAmt;

	@Column(name = "START_DATE")
	private LocalDate startDate;

	@Column(name = "END_DATE")
	private LocalDate endDate;

	@Column(name = "DENIAL_REASON")
	private String denialReason;

	@Column(name = "HOLDER_NAME")
	private String holderName;

	@Column(name = "HOLDER_SSN")
	private Long holderSsn;

}
