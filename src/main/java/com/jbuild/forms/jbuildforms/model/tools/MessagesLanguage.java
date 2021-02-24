package com.jbuild.forms.jbuildforms.model.tools;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "JBF_messages_language")
public class MessagesLanguage {

   
    @Id
    @Column(name="message_id")
    @GeneratedValue
    private Integer message_id;
    
   
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Language.class)
    @JoinColumn(name = "LANG_ID",nullable = false)
    private Language locale;
    
    @Column(name = "message_key")
    private String key;
    
    @Column(name = "message_content")
    private String content;

    public MessagesLanguage() {}
    
    public MessagesLanguage(String key,String content, Language locale) {
    	this.key = key;
    	this.content = content;
    	this.locale = locale;
    		
    }
	public Integer getMessage_id() {
		return message_id;
	}

	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}

	public Language getLocale() {
		return locale;
	}

	public void setLocale(Language locale) {
		this.locale = locale;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
    
}
