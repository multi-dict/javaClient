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
		int tempid;
		String tempiso;
		String tempname;
		for (int i = 0; i < langarray.length(); i++) {
		    JSONObject row = langarray.getJSONObject(i);
		    tempid = row.getInt("id");
		    tempiso = row.getString("ISO_2");
		    tempname = row.getString("name");
		    result.add(new Language(tempname, tempiso, tempid));
		}
		return result;
	}
}
