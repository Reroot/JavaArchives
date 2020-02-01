package com.team.alpha.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "tbl_users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
    @Size(min=3, max=50)
	@Column(nullable=true, name="username")
	private String username;
	
	@NotBlank
    @Size(min=5, max=100)
	@Column(nullable=true, name="password")
	private String password;

	@Column(nullable=true, name="display_name")
	private String displayName;

	@NaturalId
    @NotBlank
    @Size(max=50)
    @Email
	@Column(nullable=true, name="email")
	private String email;

	@Column(nullable=true, name="phone")
	private String phone;
	
	@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="user_roles", 
    	joinColumns=@JoinColumn(name="user_id"), 
    	inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(final String displayName, final String username, final String password, final String email, final String phone) {
		this.username = username;
		this.password = password;
		this.displayName = displayName;
		this.email = email;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}
	
	public String getPassword() {	
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;	
	}
	
	public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof User) {
			return Objects.equals(email, ((User) obj).getEmail())
					&& id == ((User) obj).getId()
					&& Objects.equals(phone, ((User) obj).getPhone());
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", displayName=" + displayName + ", email=" + email
				+ ", phone=" + phone + "]";
	}
	
}
