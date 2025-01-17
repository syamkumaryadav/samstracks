package com.syam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
	
	@Column(length = 5)
	@Id
	private Integer id;
	
	@Column(length = 30)
	private String name;
	
	@Column(length = 7)
	private String grade;
	
	@Column(length = 10)
	private String type;
	
}
