package com.mozahidone.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@JsonIgnore
	public String password;
	
	public String email;
	
	private Boolean active;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Role role;
	
	@PrePersist
	public void prePersist() {
		this.createdAt = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedAt = new Date();
	}
	
	
	
}
