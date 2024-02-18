package com.te.ems.dto;

import javax.persistence.Entity;

import com.te.ems.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class LoginDTO {
	private String username;
	private String password;
	

}
