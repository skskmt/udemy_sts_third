package com.example.demo.app.inquiry;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InquiryFormTest {
	
	@Size(min = 1, max = 20, message = "1-20mojide")
	private String name;
	
	@NotNull
	@Email(message = "invalid email format")
	private String email;
	
	@NotNull
	private String contents;
	
	public InquiryFormTest() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}

	
}
