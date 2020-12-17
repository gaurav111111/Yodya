package com.yupexx.bazaar.api.model.dto;

import javax.validation.constraints.NotNull;

public class UserChangePasswordDTO {
	
	@NotNull(message = "Current Password can not be null!")
	private String currentPassword;
	@NotNull(message = "New Password can not be null!")
	private String newPassword;
	@NotNull(message = "Confirm Password can not be null!")
	private String confirmPassword;
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
