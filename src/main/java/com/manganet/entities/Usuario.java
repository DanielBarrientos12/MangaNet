package com.manganet.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manganet.dto.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "username", nullable = false)
    private String username;
	
	@Column(name = "nombre")
    private String nombre;
	
	@Column(name = "email", nullable = false)
    private String email;
	
	@Column(name = "password", nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "usuarios")
    private List<Obra> obras;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	      return List.of(new SimpleGrantedAuthority((role.name())));
	    }
	
	@Override
    public boolean isAccountNonExpired() {
       return true;
    }
	
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }

}