package com.apufyp.wohkx.vetassociatesystem.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "socialNetwork")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "snTime" }, allowGetters = true)
public class SocialNetwork implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7475965858914634594L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "snId")
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "writter", referencedColumnName = "userId")
	private User writter;

	@Column(name = "descriptions", length = 600, nullable = false)
	private String descriptions;

	@Column(name = "imageType", length = 20)
	private String imageType;

	@Lob
	@Column(name = "snImage")
	private String snImage;

	@Column(name = "snTime", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date snTime;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "snId")
	@Column(name = "comments")
	private List<SNComment> comments;

	@Column(name = "timeString")
	private String timeString;

	public SocialNetwork() {
		super();
	}

	public SocialNetwork(User writter, String descriptions) {
		super();
		this.writter = writter;
		this.descriptions = descriptions;
	}

	public SocialNetwork(User writter, String descriptions, String imageType, String snImage) {
		super();
		this.writter = writter;
		this.descriptions = descriptions;
		this.imageType = imageType;
		this.snImage = snImage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getWritter() {
		return writter;
	}

	public void setWritter(User writter) {
		this.writter = writter;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getSnImage() {
		return snImage;
	}

	public void setSnImage(String snImage) {
		this.snImage = snImage;
	}

	public Date getSnTime() {
		return snTime;
	}

	public List<SNComment> getComments() {
		return comments;
	}

	public void setComments(List<SNComment> comments) {
		this.comments = comments;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

}
