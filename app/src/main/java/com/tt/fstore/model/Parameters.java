package com.tt.fstore.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Parameters {

	@SerializedName("role")
	private String role;

	@SerializedName("is_active")
	private boolean isActive;

	@SerializedName("gender")
	private String gender;

	@SerializedName("login_type")
	private String loginType;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("profile_picture")
	private String profilePicture;

	@SerializedName("primary_phone")
	private String primaryPhone;

	@SerializedName("token")
	private String token;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("password_hash")
	private String passwordHash;

	@SerializedName("primary_email")
	private String primaryEmail;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("user_key")
	private String user_key;

	public String getUser_key() {
		return user_key;
	}

	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setIsActive(boolean isActive){
		this.isActive = isActive;
	}

	public boolean isIsActive(){
		return isActive;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setLoginType(String loginType){
		this.loginType = loginType;
	}

	public String getLoginType(){
		return loginType;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setProfilePicture(String profilePicture){
		this.profilePicture = profilePicture;
	}

	public String getProfilePicture(){
		return profilePicture;
	}

	public void setPrimaryPhone(String primaryPhone){
		this.primaryPhone = primaryPhone;
	}

	public String getPrimaryPhone(){
		return primaryPhone;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setPasswordHash(String passwordHash){
		this.passwordHash = passwordHash;
	}

	public String getPasswordHash(){
		return passwordHash;
	}

	public void setPrimaryEmail(String primaryEmail){
		this.primaryEmail = primaryEmail;
	}

	public String getPrimaryEmail(){
		return primaryEmail;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

}