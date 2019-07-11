package com.amdocs.ensemble.employeeengagement.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Cell {
	
	@Id
	@GeneratedValue
	private Long Id;
	
	private String title;
	
	private String type;
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy="cell")
	private List<CellFilter> filters;
	
	@Column
	@ElementCollection	
	private List<String> fields;
	
	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	protected Cell() {
		
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<CellFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<CellFilter> filters) {
		this.filters = filters;
	}

	@Override
	public String toString() {
		return "Cell [Id=" + Id + ", title=" + title + ", type=" + type + ", filters=" + filters + ", fields=" + fields
				+ "]";
	}
	
	
	
}
