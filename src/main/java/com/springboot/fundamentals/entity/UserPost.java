package com.springboot.fundamentals.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//Modelo de entidades con JPA -> Permite modelar bases de datos como objetos
@Entity
//Se asigna nombre a la tabla
@Table(name = "userpost")
public class UserPost {
	//S debe agregar un ID
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //identity para que no se siga el contador de otros 
	//Creación de las columnas de la tabala
	@Column(name = "id_post", nullable = false, unique = true)
    private Long id;
    @Column(name = "description", length = 250)
    private String description;
    @ManyToOne //Esto quiere decir que un usuario puede tener muchos post
    private User user;
    
    public UserPost() {
    	
    }

	public UserPost(String description, User user) {
		this.description = description;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
	@Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
    
}
