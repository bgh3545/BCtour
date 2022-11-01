package com.greenart.ch1;

import java.util.Objects;

public class RollComment {

	String name;
	String comment;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public int hashCode() {
		return Objects.hash(comment, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RollComment other = (RollComment) obj;
		return Objects.equals(comment, other.comment) && Objects.equals(name, other.name);
	}
	@Override
	public String toString() {
		return "RollComment [name=" + name + ", comment=" + comment + "]";
	}
	public RollComment(String name, String comment) {
		super();
		this.name = name;
		this.comment = comment;
	}
	
	public RollComment() {}
}
