package com.itl.domain.entities.custservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.envers.Audited;

import com.itl.domain.entities.base.Base;

@Entity
@Audited(auditParents = { Base.class })
@NamedQueries({ @NamedQuery(name = "CustomerCategoryTypeMst.getUniqueCustomerCategoryType", query = "SELECT e FROM CustomerCategoryTypeMst e WHERE e.id=:Id"),
		@NamedQuery(name = "CustomerCategoryTypeMst.getcustomerCategoryTypeName", query = "SELECT e FROM CustomerCategoryTypeMst e WHERE e.customerCategoryTypeName=:customerCategoryTypeName"),
		@NamedQuery(name = "CustomerCategoryTypeMst.getCustomerCategoryTypeId", query = "SELECT e FROM CustomerCategoryTypeMst e WHERE e.customerCategoryTypeId=:customerCategoryTypeId"),
		@NamedQuery(name = "CustomerCategoryTypeMst.getByAuthStatus", query = "SELECT e FROM CustomerCategoryTypeMst e WHERE e.authStatus=:authStatus")
})
public class CustomerCategoryTypeMst extends Base {

	private static final long serialVersionUID = -1L;

	@Column(nullable = false, length = 8)
	private String customerCategoryTypeId = "";

	@Column(nullable = false, length = 48)
	private String customerCategoryTypeName = "";

	@Column(nullable = false, length = 48)
	private String customerCategoryTypeDisplayName = "";

	@Column(nullable = true)
	private Integer isActive = 1;

	@Column(nullable = false, length = 8)
	private String authStatus="";

	public String getCustomerCategoryTypeId() {
		return customerCategoryTypeId;
	}

	public void setCustomerCategoryTypeId(String customerCategoryTypeId) {
		this.customerCategoryTypeId = customerCategoryTypeId;
	}

	public String getCustomerCategoryTypeName() {
		return customerCategoryTypeName;
	}

	public void setCustomerCategoryTypeName(String customerCategoryTypeName) {
		this.customerCategoryTypeName = customerCategoryTypeName;
	}

	public String getCustomerCategoryTypeDisplayName() {
		return customerCategoryTypeDisplayName;
	}

	public void setCustomerCategoryTypeDisplayName(String customerCategoryTypeDisplayName) {
		this.customerCategoryTypeDisplayName = customerCategoryTypeDisplayName;
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
}
