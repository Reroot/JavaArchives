package com.ss.lms.models;

import java.io.Serializable;

/**
 * POJO class to hold all the author fields.
 * 
 * @author ryanb
 *
 */
public class Author implements Serializable, Comparable<Author> {

	private static final long serialVersionUID = -1743176290669356636L;
	
	private Integer id;
	private String name;
	
	public Author(Integer id, String name) {
		this.name = name;
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override 
	public String toString() {
		return this.id + "/" + this.name;
	}
	
	@Override
	public int compareTo(Author a) {
		return this.getId() - a.getId();
	}
	
	@Override
	public boolean equals(Object a) {
		if (a == this) {
			return true;
		}
		
		if (a instanceof Author) {
			Author newA = (Author) a;
			if (newA.name.toLowerCase().equals(this.name.toLowerCase())){
				return true;
			}
		}
		
		return false;
	}
}
