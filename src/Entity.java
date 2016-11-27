import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Entity  {
	public static String entityPath;
	private int id;
	private ArrayList<Word> words;

	public Entity(int tempid, ArrayList<Word> tempWords) {
		this.id = tempid;
		this.words = tempWords;
	}

	private static void SetEntityPath(String baseURL, LoginData user, Dictionary dict) {
		Entity.entityPath = "http://" + baseURL + "/entities?session_token=" + user.GetSessionID() + "&dictionary_id=" + dict.GetID();
	}

	public static ArrayList<Entity> GetEntities(LoginData user, Dictionary dict) throws Exception {
		Entity.SetEntityPath(Application.baseURL, user, dict);
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		System.out.println(Entity.entityPath);
		HttpGet request = new HttpGet(Entity.entityPath);
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
			return GetEntites(jsonresult);

		} else {
			return null;
		}
	}

	private static ArrayList<Entity> GetEntites(JSONObject jsonresult) throws JSONException {
		ArrayList<Entity> result = new ArrayList<Entity>();
		JSONArray entitiesArray = jsonresult.getJSONArray("entities");
		for (int i = 0; i < entitiesArray.length(); i++) {
		    JSONObject row = entitiesArray.getJSONObject(i);
		    int tempid = row.getInt("id");
		    JSONArray wordsArray = row.getJSONArray("words");
		    ArrayList<Word> tempWords = Word.GetWordsFromJsonArray(wordsArray);
		    result.add(new Entity(tempid, tempWords));
		}
		return result;
	}
	public Words GetWords(){
		return new Words(this.words,this);
	}
	public static ArrayList<Entity> Filter(ArrayList<Entity> origin, String filter){
		ArrayList<Entity> result = new ArrayList<Entity> ();
		for(Entity e : origin){
			if(e.GetWords().Contains(filter)){
				result.add(e);
			}
		}
		return result;
	}

	public int GetID() {
		return this.id;
	}
}
