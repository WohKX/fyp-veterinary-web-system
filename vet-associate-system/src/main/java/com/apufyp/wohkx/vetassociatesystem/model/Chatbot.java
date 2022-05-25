package com.apufyp.wohkx.vetassociatesystem.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author wohkx
 *
 */
@Entity
@Table(name = "chatbots")
@EntityListeners(AuditingEntityListener.class)
public class Chatbot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4305589514634311801L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "classes", length = 30)
	private String classes;

	@Column(name = "questions", length = 255, nullable = false)
	private String questions;

	@Column(name = "answers", length = 2500, nullable = false)
	private String answers;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "editors", referencedColumnName = "userId")
	private Admin editors;

	public Chatbot() {
		super();
	}
	
	public Chatbot(String classes, String questions, String answers) {
		super();
		this.classes = classes;
		this.questions = questions;
		this.answers = answers;
	}

	public Chatbot(String questions, String answers, Admin editors) {
		super();
		this.questions = questions;
		this.answers = answers;
		this.editors = editors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public Admin getEditors() {
		return editors;
	}

	public void setEditors(Admin editors) {
		this.editors = editors;
	}

}