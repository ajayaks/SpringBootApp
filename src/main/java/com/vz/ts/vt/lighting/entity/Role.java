package com.vz.ts.vt.lighting.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="T_ROLE")
public class Role implements Serializable {
	
	private static final long serialVersionUID = 15735943957676L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "ROLE_NAME",unique = true, nullable = false, length = 250)
    @NotEmpty
    private String roleName;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
    		name="T_ROLE_PERMISSION",
    		joinColumns= @JoinColumn(name="ROLE_ID", referencedColumnName="ID"),
    		inverseJoinColumns= @JoinColumn(name="PERMISSION_ID", referencedColumnName="ID"), 
    		uniqueConstraints=	@UniqueConstraint(columnNames={"ROLE_ID", "PERMISSION_ID"})
    )
    private List<Permission> permissions;
    
	public Role() {
		super();
	}
	
	public Role(Long id, String roleName, List<Permission> permissions) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.permissions = permissions;
	}
	public Role(Long id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}
	
	public Role(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}	
	
	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
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
		Role other = (Role) obj;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + roleName + ", permissions=" + permissions + "]";
	}

	
}
