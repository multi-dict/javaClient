import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Language {
	private String name;
	private String iso2;
	private int id;
	
	public Language (String n, String i2, int id){
		this.name = n;
		this.iso2 = i2;
		this.id = id;
	}
	public static ArrayList<Language> GetLanguagesFromJsonArray(JSONArray langarray) throws JSONException {
		ArrayList<Language> result = new ArrayList<Language>();
		for (int i = 0; i < langarray.length(); i++) {
		    JSONObject row = langarray.getJSONObject(i);
		    result.add(new Language(row));
		}
		return result;
	}
	public Language (JSONObject obj){
	    try {
			this.id = obj.getInt("id");
		    this.iso2 = obj.getString("ISO_2");
		    this.name = obj.getString("name");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String GetName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	public String GetISO() {
		return this.iso2;
	}
	public int GetID() {
		return this.id;
	}
}
