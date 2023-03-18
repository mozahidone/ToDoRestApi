package com.mozahidone.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TEXT")
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="post_id")
	@JsonIgnore
	private Post post;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
}
