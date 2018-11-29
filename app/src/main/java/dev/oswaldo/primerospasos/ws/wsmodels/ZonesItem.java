package dev.oswaldo.primerospasos.ws.wsmodels;

public class ZonesItem{
	private String nameZone;
	private String nameState;
	private String idZone;

	public void setNameZone(String nameZone){
		this.nameZone = nameZone;
	}

	public String getNameZone(){
		return nameZone;
	}

	public void setNameState(String nameState){
		this.nameState = nameState;
	}

	public String getNameState(){
		return nameState;
	}

	public void setIdZone(String idZone){
		this.idZone = idZone;
	}

	public String getIdZone(){
		return idZone;
	}

	@Override
 	public String toString(){
		return 
			"ZonesItem{" + 
			"nameZone = '" + nameZone + '\'' + 
			",nameState = '" + nameState + '\'' + 
			",idZone = '" + idZone + '\'' + 
			"}";
		}
}
