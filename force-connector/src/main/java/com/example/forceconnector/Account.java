package com.example.forceconnector;

import java.security.KeyStore.Entry.Attribute;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
@JsonProperty("attributes")
private Attribute attributes;
@JsonProperty("Id")
private String id;
@JsonProperty("Name")
private String name;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();
 
@JsonProperty("attributes")
public Attribute getAttributes() {
return attributes;
}
 
@JsonProperty("attributes")
public void setAttributes(Attribute attributes) {
this.attributes = attributes;
}
 
@JsonProperty("Id")
public String getId() {
return id;
}
 
@JsonProperty("Id")
public void setId(String id) {
this.id = id;
}
 
@JsonProperty("Name")
public String getName() {
return name;
}
 
@JsonProperty("Name")
public void setName(String name) {
this.name = name;
}
 
@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}
 
@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}
}
