package models;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import main.Application;

public class Dictionary {
	public static String dictPath;
	private String name;
	private ArrayList<Language> languages;
	private int id;

	public Dictionary(String name, int id, ArrayList<Language> lan) {
		this.name=name;
		this.id = id;
		this.languages= lan;
	}

	private static void SetGetDictionariesPath(String baseURL, LoginData user) throws Exception {
		Dictionary.dictPath = "http://" + baseURL + "/dictionaries?session_token=" + user.GetSessionID();
	}
	
	private static ArrayList<Dictionary> GetDictionaries(JSONObject json) throws Exception{
		ArrayList<Dictionary> result = new ArrayList<Dictionary>();
		JSONArray jsonarray = json.getJSONArray("dictionaries");
		String tempname;
		ArrayList<Language> templang; 
		int tempid;
		for (int i = 0; i < jsonarray.length(); i++) {
		    JSONObject row = jsonarray.getJSONObject(i);
		    tempname = row.getString("name");
		    tempid = row.getInt("id");
		    JSONArray langarray = row.getJSONArray("languages");
		    templang = Language.GetLanguagesFromJsonArray(langarray);
		    result.add(new Dictionary(tempname,tempid,templang));
		}
		return result;

	}
	
	public static ArrayList<Dictionary> GetDictionaries(LoginData user) throws Exception {
		Dictionary.SetGetDictionariesPath(Application.baseURL, user);
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		HttpGet request = new HttpGet(Dictionary.dictPath);
		request.addHeader("content-type", "application/json");

		HttpResponse response = httpClient.execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == 200) {
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer resultbuffer = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				resultbuffer.append(line);
			}
			JSONObject jsonresult = new JSONObject(resultbuffer.toString());
			return GetDictionaries(jsonresult);
			

		}
		else {
			return null;
		}
	}
	public String toString(){
		return this.name;
	}

	public String GetName() {
		return this.name;
	}

	public int GetID() {
		return this.id;
	}

	public ArrayList<Language> GetLanguages() {
		return this.languages;
	}
}
