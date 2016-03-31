package com.vz.ts.vt.lighting.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="T_CLIENT_INFO")
public class ClientInfo implements Serializable{
	
	private static final long serialVersionUID = 5950422376487881876L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "CLIENT_ID", unique = true, nullable = false, length = 250)
    @NotEmpty
    private String clientId;
    
    @Column(name = "CLIENT_NAME", nullable = false, length = 250)
    @NotEmpty
    private String clientName;
    
    public ClientInfo() {
		super();
	}

	public ClientInfo(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

}
