package models;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class EntityTest {

	@Test
	public void test1() {
		Language l = new Language("", "", 1);
		Language l2 = new Language("", "", 2);
		Word w1 = new Word(null, l, null);
		Word w2 = new Word(null, l, null);
		Word w3 = new Word(null, l2, null);
		ArrayList<Word> alw = new ArrayList<Word>();
		alw.add(w1);
		alw.add(w2);
		alw.add(w3);
		Entity e = new Entity(1, alw);
		Assert.assertEquals(1, e.GetID());
		
	}
	
	@Test
	public void test2() {
		Language l = new Language("", "", 1);
		Language l2 = new Language("", "", 2);
		Word w1 = new Word(null, l, null);
		Word w2 = new Word(null, l, null);
		Word w3 = new Word(null, l2, null);
		ArrayList<Word> alw = new ArrayList<Word>();
		alw.add(w1);
		alw.add(w2);
		alw.add(w3);
		Entity e = new Entity(0, alw);
		Assert.assertEquals(3, e.GetWords().getWords().size());
		
	}
	
	@Test
	public void testfilter() {
		Language l = new Language("", "", 1);
		Language l2 = new Language("", "", 2);
		Word w1 = new Word("asd", l, null);
		Word w2 = new Word("3filterasd3", l, null);
		Word w3 = new Word("filaska", l2, null);
		ArrayList<Word> alw = new ArrayList<Word>();
		alw.add(w1);
		alw.add(w2);
		alw.add(w3);
		Entity e = new Entity(0, alw);
		ArrayList<Entity> es = new ArrayList<Entity> ();
		es.add(e);
		Assert.assertEquals(1, Entity.Filter(es, "asd").size());
		
	}
	
	@Test
	public void testfilter2() {
		Language l = new Language("", "", 1);
		Language l2 = new Language("", "", 2);
		Word w1 = new Word("asd", l, null);
		Word w2 = new Word("3filter3", l, null);
		Word w3 = new Word(null, l2, null);
		ArrayList<Word> alw = new ArrayList<Word>();
		alw.add(w1);
		alw.add(w2);
		alw.add(w3);
		Entity e = new Entity(0, alw);
		ArrayList<Entity> es = new ArrayList<Entity> ();
		es.add(e);
		Assert.assertEquals(1, Entity.Filter(es, "filter").size());
		
	}

}
