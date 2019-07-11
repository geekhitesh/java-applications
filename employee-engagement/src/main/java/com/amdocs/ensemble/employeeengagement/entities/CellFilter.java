package com.amdocs.ensemble.employeeengagement.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class CellFilter {
	
	@Id
	@GeneratedValue
	private Long Id;
	
	public Cell getCell() {
		return cell;
	}
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	
	@Column
	private String keyColumn;
	
	@Column
	@ElementCollection
	private List<String> filterValues;
	private String type;
	private String relation;
	
	@ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn
    @JsonIgnore
	private Cell cell;
	
	protected CellFilter() {
		
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getKeyColumn() {
		return keyColumn;
	}
	public void setKeyColumn(String key) {
		this.keyColumn = key;
	}
	public List<String> getFilterValues() {
		return filterValues;
	}
	public void setFilterValues(List<String> values) {
		this.filterValues = values;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	@Override
	public String toString() {
		return "CellFilter [Id=" + Id + ", key=" + keyColumn + ", values=" + filterValues + ", type=" + type + ", relation="
				+ relation + "]";
	}
	
	

}
