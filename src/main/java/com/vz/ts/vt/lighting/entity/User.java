package com.vz.ts.vt.lighting.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "T_USER")
public class User implements Serializable{
	
	private static final long serialVersionUID = 12812801920192090L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "USER_ID", unique = true, nullable = false)
	private String userId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "AUTH_TOKEN" , unique = true, length=1000)
	private String authToken;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_details_id")
	private UserDetails userDetails;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CLIENT_INFO_ID")
	private ClientInfo clientInfo;
	

	@ManyToOne(optional = true, fetch=FetchType.EAGER)
    @JoinColumn(name ="ROLE_ID")
    private Role roles;
	
	@Column(name="CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	public User() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", userId=" + userId + ", password=" + password + ", authToken="
				+ authToken + ", userDetails=" + userDetails + ", clientInfo=" + clientInfo + ", roles=" + roles
				+ ", createdDate=" + createdDate + "]";
	}
	
}
