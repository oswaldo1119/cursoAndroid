package dev.oswaldo.primerospasos.ws.wsmodels;

import java.util.List;

public class UserResponse{
	private UserInfo userInfo;
	private int success;
	private String message;
	private List<ZonesItem> zones;

	public void setUserInfo(UserInfo userInfo){
		this.userInfo = userInfo;
	}

	public UserInfo getUserInfo(){
		return userInfo;
	}

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setZones(List<ZonesItem> zones){
		this.zones = zones;
	}

	public List<ZonesItem> getZones(){
		return zones;
	}

	@Override
 	public String toString(){
		return 
			"UserResponse{" + 
			"userInfo = '" + userInfo + '\'' + 
			",success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			",zones = '" + zones + '\'' + 
			"}";
		}
}