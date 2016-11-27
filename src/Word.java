import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Word {
	private Language language;
	private String word;
	private String description;
	private int id;
	private static String delPath;
	private static String addPath;
	private static String editPath;

	public Word(String tempword, int tempid, Language templang, String tempdisc) {
		this.word = tempword;
		this.id = tempid;
		this.language = templang;
		this.description = tempdisc;

	}

	public Word(String tempword, Language templlang, String tempdisc) {
		this.word = tempword;
		this.id = 0;
		this.language = templlang;
		this.description = tempdisc;
	}

	public static ArrayList<Word> GetWordsFromJsonArray(JSONArray wordsArray) throws JSONException {
		ArrayList<Word> result = new ArrayList<Word>();
		for (int i = 0; i < wordsArray.length(); i++) {
			JSONObject row = wordsArray.getJSONObject(i);
			String tempword = row.getString("word");
			int tempid = row.getInt("id");
			String tempdesc = row.getString("description");
			Language templang = new Language(row.getJSONObject("language"));
			result.add(new Word(tempword, tempid, templang, tempdesc));
		}
		return result;
	}

	public static ArrayList<Word> GetWordByLanguage(Words Words, Language lang) {
		ArrayList<Word> worlds = new ArrayList<Word>();
		for (Word w : Words.getWords()) {
			if (w.language.GetID() == lang.GetID()) {
				worlds.add(w);
			}
		}
		return worlds;
	}

	public String GetWord() {
		return this.word;
	}

	public String GetDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		return this.word;
	}
	
	public Language GetLanguage(){
		return this.language;
	}
	
	public int postDelete(LoginData user) throws IOException {
		Word.SetDelPath(user, this);

		int statusCode = 0;

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpDelete request = new HttpDelete(Word.delPath);
			request.addHeader("content-type", "application/json");
			// handle response here
			HttpResponse response = httpClient.execute(request);
			statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {

			} else {
				System.out.println(statusCode);
				throw new Exception();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			httpClient.close();
		}
		return statusCode;
	}

	private static void SetDelPath(LoginData user, Word w) {
		Word.delPath = "http://" + Application.baseURL + "/words/" + w.id + "?session_token=" + user.GetSessionID();
	}

	public int postAdd(int entityId, LoginData user) throws JSONException, IOException {
		Word.SetAddPath(user);

		JSONObject jsonParam = new JSONObject();
		jsonParam.put("session_token", user.GetSessionID());
		jsonParam.put("description", this.description);
		jsonParam.put("entity_id", entityId);
		jsonParam.put("language_id", this.language.GetID());
		jsonParam.put("word", this.word);

		int statusCode = 0;

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPost request = new HttpPost(Word.addPath);
			StringEntity params = new StringEntity(jsonParam.toString());
			request.addHeader("content-type", "application/json");
			request.setEntity(params);
			// handle response here
			HttpResponse response = httpClient.execute(request);
			statusCode = response.getStatusLine().getStatusCode();
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			System.out.println(result.toString());
			if (statusCode == 200) {

			} else {
				System.out.println(statusCode);
				throw new Exception();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			httpClient.close();
		}
		return statusCode;
	}

	public int postEdit(LoginData user, Word original) throws JSONException, IOException {
		Word.SetEditPath(original);

		JSONObject jsonParam = new JSONObject();

		jsonParam.put("session_token", user.GetSessionID());
		if (original.GetWord() != this.GetWord()) {
			jsonParam.put("word", this.word);
		}
		if (original.GetDescription() != this.GetDescription()) {
			jsonParam.put("description", this.description);
		}


		int statusCode = 0;

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPut request = new HttpPut(Word.editPath);
			StringEntity params = new StringEntity(jsonParam.toString());
			request.addHeader("content-type", "application/json");
			request.setEntity(params);
			// handle response here
			HttpResponse response = httpClient.execute(request);
			statusCode = response.getStatusLine().getStatusCode();
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			System.out.println(result.toString());
			if (statusCode == 200) {

			} else {
				System.out.println(statusCode);
				throw new Exception();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			httpClient.close();
		}
		return statusCode;
	}

	private static void SetEditPath(Word w) {
		Word.editPath = "http://" + Application.baseURL + "/words/" + w.GetID();
	}

	private int GetID() {
		return this.id;
	}

	private static void SetAddPath(LoginData user) {
		Word.addPath = "http://" + Application.baseURL + "/words";
	}

}
