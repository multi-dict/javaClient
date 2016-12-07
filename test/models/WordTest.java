package models;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class WordTest {

	
	@Test
	public void GetWordsByLanguageTest() throws JSONException {
		Language l = new Language("", "", 1);
		Language l2 = new Language("", "", 2);
		Word w1 = new Word(null, l, null);
		Word w2 = new Word(null, l, null);
		Word w3 = new Word(null, l2, null);
		ArrayList<Word> alw = new ArrayList<Word>();
		alw.add(w1);
		alw.add(w2);
		alw.add(w3);
		Words ws = new Words(alw, new Entity(0, alw));
		alw = Word.GetWordByLanguage(ws, l);
		Assert.assertEquals(2,alw.size());
	}
	
	@Test
	public void GetWordsByLanguageTest2() throws JSONException {
		Language l = new Language("", "", 1);
		Language l2 = new Language("", "", 2);
		Word w1 = new Word(null, l, null);
		Word w2 = new Word(null, l, null);
		Word w3 = new Word(null, l2, null);
		ArrayList<Word> alw = new ArrayList<Word>();
		alw.add(w1);
		alw.add(w2);
		alw.add(w3);
		Words ws = new Words(alw, new Entity(0, alw));
		ArrayList<Word> alw2 = Word.GetWordByLanguage(ws, l2);
		Assert.assertEquals(1,alw2.size());
	}


}
