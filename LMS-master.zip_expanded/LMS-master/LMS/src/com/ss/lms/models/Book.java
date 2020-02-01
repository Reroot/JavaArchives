package com.ss.lms.models;

import java.io.Serializable;

/**
 * POJO class to hold all the book fields.
 * 
 * @author ryanb
 *
 */
public class Book implements Serializable, Comparable<Book> {

	private static final long serialVersionUID = 4545693426420584112L;
	
	private Integer id;
	private String name;
	private Integer authorId;
	private Integer publisherId;

	public Book(Integer id, String name, Integer authorId, Integer publisherId){
		this.name = name;
		this.authorId = authorId;
		this.publisherId = publisherId;
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

	/**
	 * @return the authorId
	 */
	public Integer getAuthorId() {
		return authorId;
	}

	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	/**
	 * @return the publisherId
	 */
	public Integer getPublisherId() {
		return publisherId;
	}

	/**
	 * @param publisherId the publisherId to set
	 */
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return this book's ID number
	 */
	public Integer getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return this.id + "/" + this.name + "/" + this.authorId + "/" + this.publisherId;
	}
	
	@Override
	public boolean equals(Object b) {
		if (b == this) {
			return true;
		}
		
		if (b instanceof Book) {
			Book newB = (Book) b;
			//same as newB.name.toLowerCase().equals(this.name.toLowerCase())
			if (newB.name.equalsIgnoreCase(this.name) && 
					newB.authorId.equals(this.authorId) 
					&& newB.publisherId.equals(this.publisherId)){
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(Book b) {
		return this.getId() - b.getId();
	}
}
