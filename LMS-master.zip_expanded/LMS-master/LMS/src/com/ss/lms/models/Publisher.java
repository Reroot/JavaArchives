package com.ss.lms.models;

import java.io.Serializable;

/**
 * POJO class to hold all the publisher fields.
 * 
 * @author ryanb
 *
 */
public class Publisher implements Serializable, Comparable<Publisher>{

	private static final long serialVersionUID = 3770257710208267543L;
	
	private Integer id;
	private String name;
	private String address;
	
	public Publisher(Integer id, String name, String address){
		this.name = name;
		this.address = address;
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

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	@Override 
	public String toString() {
		return this.id + "/" + this.name + "/" + this.address;
	}
	
	@Override
	public boolean equals(Object p) {
		if (p == this) {
			return true;
		}
		
		if (p instanceof Publisher) {
			Publisher newP = (Publisher) p;
			if (newP.name.toLowerCase().equals(this.name.toLowerCase()) 
					&& newP.address.toLowerCase().equals(this.address.toLowerCase())){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int compareTo(Publisher p) {
		return this.getId() - p.getId();
	}
}
