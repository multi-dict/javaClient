import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Word {
	private Language language;
	private String word;
	private int id;
	
	public Word(String tempword, int tempid, Language templang) {
		this.word = tempword;
		this.id = tempid;
		this.language=templang;

	}

	public static ArrayList<Word> GetWordsFromJsonArray(JSONArray wordsArray) throws JSONException {
		ArrayList<Word> result = new ArrayList<Word>();
		for (int i = 0; i < wordsArray.length(); i++) {
		    JSONObject row = wordsArray.getJSONObject(i);
		    String tempword = row.getString("word");
		    int tempid = row.getInt("id");
		    Language templang = new Language(row.getJSONObject("language"));
		    result.add(new Word(tempword,tempid,templang));
		}
		return result;
	}

}
