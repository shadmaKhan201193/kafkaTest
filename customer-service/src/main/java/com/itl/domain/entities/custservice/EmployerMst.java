package com.itl.domain.entities.custservice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;

import com.itl.domain.entities.base.Base;

@Entity
@Audited(auditParents = { Base.class })
	@NamedQueries({
	@NamedQuery(name="EmployerMst.getUniqueOrgType", query = "SELECT e FROM EmployerMst e WHERE e.id=:Id"),
	@NamedQuery(name="EmployerMst.getemployerName", query = "SELECT e FROM EmployerMst e WHERE e.nameOfOrg=:employerName"),
	@NamedQuery(name="EmployerMst.getemployerId", query = "SELECT e FROM EmployerMst e WHERE e.employerId=:employerId")
})
public class EmployerMst extends Base {

	private static final long serialVersionUID = -1L;

	@Column(nullable = false,length = 8)
	private String employerId = "";
	
	@Column(nullable = false,length = 48)
	private String nameOfOrg ="";
	
	@Column(nullable = false,length = 48)
	private String employerDisplayName ="";
	
	@Column(nullable = false,length = 8)
	private String typeOfOrg="";
	
	@Column(nullable = false,length = 48)
	private String tanno = "";
	
	@Column(nullable = false,length = 8)
	private String sector="";
	
	@Column(nullable = false,length = 8)
	private String industry="";
	
	@Column(nullable = false,length = 24)
	private String gst ="";
	
	@Column(nullable = false,length = 24)
	private String cin="";

	@Column(nullable = true)
	private Integer isActive = 1;

	@Column(nullable = false,length = 8)
	private String authStatus="";
	
	@OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<EmployerDetail> employeeDetail = new ArrayList<EmployerDetail>();
	
	public String getEmployerId() {
		return employerId;
	}

	public void setEmployerId(String employerId) {
		this.employerId = employerId;
	}

	public String getNameOfOrg() {
		return nameOfOrg;
	}

	public void setNameOfOrg(String nameOfOrg) {
		this.nameOfOrg = nameOfOrg;
	}

	public String getEmployerDisplayName() {
		return employerDisplayName;
	}

	public void setEmployerDisplayName(String employerDisplayName) {
		this.employerDisplayName = employerDisplayName;
	}

	public String getTypeOfOrg() {
		return typeOfOrg;
	}

	public void setTypeOfOrg(String typeOfOrg) {
		this.typeOfOrg = typeOfOrg;
	}

	public String getTanno() {
		return tanno;
	}

	public void setTanno(String tanno) {
		this.tanno = tanno;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public List<EmployerDetail> getEmployeeDetail() {
		return employeeDetail;
	}

	public void setEmployeeDetail(List<EmployerDetail> employeeDetail) {
		this.employeeDetail = employeeDetail;
	}
}
