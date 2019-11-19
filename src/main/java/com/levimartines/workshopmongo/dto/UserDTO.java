package com.levimartines.workshopmongo.dto;

import com.levimartines.workshopmongo.domain.User;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;

	public UserDTO() {
	}

	public UserDTO (User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public UserDTO(String name, String email) {
		this.name = name;
		this.email = email;
	}
}
