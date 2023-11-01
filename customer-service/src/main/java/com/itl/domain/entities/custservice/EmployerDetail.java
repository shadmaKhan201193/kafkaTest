package com.itl.domain.entities.custservice;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.itl.domain.entities.base.Base;
import com.itl.domain.entities.masters.Address;

@Entity
@Audited(auditParents = { Base.class })
public class EmployerDetail extends Base {
	
	private static final long serialVersionUID = -1L;
	
	@Column(nullable = false)
	private Integer custSrNo=1;
	
	@Column(nullable = false, length = 8)
	private String addressType="";

	@ManyToOne
	@JoinColumn(name = "fkEmployerPk")
	@LazyCollection(LazyCollectionOption.FALSE)
	private EmployerMst employer;
	
	@NotAudited
	@ElementCollection
	@JoinTable(name = "EmployerAddress", joinColumns = @JoinColumn(name = "EmployerDetail_ID"))
	@GenericGenerator(name = "sequence_gen", strategy = "sequence")
	@CollectionId(columns = { @Column(name = "Id") }, generator = "sequence_gen", type = @Type(type = "long"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Address> employerAddresses = new ArrayList<Address>();

	public Integer getCustSrNo() {
		return custSrNo;
	}

	public void setCustSrNo(Integer custSrNo) {
		this.custSrNo = custSrNo;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public EmployerMst getEmployer() {
		return employer;
	}

	public void setEmployer(EmployerMst employer) {
		this.employer = employer;
	}

	public Collection<Address> getEmployerAddresses() {
		return employerAddresses;
	}

	public void setEmployerAddresses(Collection<Address> employerAddresses) {
		this.employerAddresses = employerAddresses;
	}
}
