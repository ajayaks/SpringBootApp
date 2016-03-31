package com.vz.ts.vt.lighting.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="T_PERMISSION")
public class Permission implements Serializable {
	
	private static final long serialVersionUID = 12974923749723947L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "PERMISSION_NAME",unique = true, nullable = false, length = 250)
    @NotEmpty
    private String permissionName;

  	public Permission() {
		super();
	}
	
	public Permission(Long id, String permissionName) {
		super();
		this.id = id;
		this.permissionName = permissionName;
		//this.active = active;		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setName(String permissionName) {
		this.permissionName = permissionName;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((permissionName == null) ? 0 : permissionName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permission other = (Permission) obj;
		if (permissionName == null) {
			if (other.permissionName != null)
				return false;
		} else if (!permissionName.equals(other.permissionName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + permissionName + "]";
	}

}
