package fr.gie.extracteurBin.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LivrableData {

	@Id
	private long id;
	private String name;
	private String url;
	private String source;

	public LivrableData() {
		super();
	}

	public LivrableData(long id, String name, String url, String source) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.source = source;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "LivrableData [id=" + id + ", name=" + name + ", url=" + url + ", source=" + source + "]";
	}

}
