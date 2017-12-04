package com.nisum.carpool.service.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailAccount {

	@Value("${gmail.email}")
	private String adminemail;

	@Value("${gmail.password}")
	private String adminpassword;

	public String getAdminemail() {
		return adminemail;
	}

	public void setAdminemail(String adminemail) {
		this.adminemail = adminemail;
	}

	public String getAdminpassword() {
		return adminpassword;
	}

	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}
}
