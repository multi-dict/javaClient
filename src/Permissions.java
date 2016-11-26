import org.json.JSONArray;
import org.json.JSONObject;

public class Permissions {
	private boolean canedit = false;
	private boolean canadd = false;
	private boolean candelete = false;
	private boolean superuser = false;
	
	public Permissions(JSONObject json){
		try{
			JSONArray testarray = json.getJSONArray("permissions");
			String codeName;
			for (int i = 0; i < testarray.length(); i++) {
			    JSONObject row = testarray.getJSONObject(i);
			    codeName = row.getString("codename");
			    switch (codeName) {
			    case "add_word" :
			    	this.canadd = true;
			    	break;
			    case "change_word" :
			    	this.canedit = true;
			    	break;
			    case "delete_word" :
			    	this.candelete = true;
			    	break;
			    }
			}
			if (json.getInt("is_superuser")==1){
				this.superuser = true;
			}
			else{
				this.superuser = false;
			}
		}
		catch (Exception ex){
			
		}
	}
	public boolean CanEdit(){
		return this.canedit;
	}
	public boolean CanAdd(){
		return this.canadd;
	}
	public boolean IsSuperUser(){
		return this.superuser;
	}
	public boolean CanDelete(){
		return this.candelete;
	}
}
