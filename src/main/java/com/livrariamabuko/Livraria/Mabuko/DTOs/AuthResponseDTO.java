package com.livrariamabuko.Livraria.Mabuko.DTOs;

import java.io.Serializable;
import java.util.Set;

import com.livrariamabuko.Livraria.Mabuko.model.UserRole;

public class AuthResponseDTO implements Serializable{
    private static final long serialVersionUID = -1250867815652061380L;

    private Long id;
	private String name;
	private String username;
	private String token;
	private Set<UserRole> roles;
//	
	


	public AuthResponseDTO() {

	}

	public AuthResponseDTO(Long id, String name, String username, String token) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.token = token;
		
	}
	
    
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
    
}
