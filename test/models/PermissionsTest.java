package models;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class PermissionsTest {

	@Test
	public void ConstructorTest1() throws JSONException {
		String testString = "{'session_token': '5ae4413f2428459c9412960dc686cad8', 'permissions': [{'codename': 'add_word', 'name': 'Can add Word'}, {'codename': 'change_word', 'name': 'Can change Word'}, {'codename': 'delete_word', 'name': 'Can delete Word'}], 'is_superuser': 0}".replace('`', '"');
		Permissions p = new Permissions(new JSONObject(testString));
		Assert.assertEquals(p.CanAdd(),true);
		Assert.assertEquals(p.CanDelete(),true);
		Assert.assertNotEquals(p.IsSuperUser(), true);
	}
	
	@Test
	public void ConstructorTest2() throws JSONException{
		String testString = "{'session_token': '2866812fd0604044840c8874569dbb60', 'permissions': [], 'is_superuser': 0}".replace('`', '"');
		Permissions p = new Permissions(new JSONObject(testString));
		Assert.assertEquals(p.CanAdd(),false);
		Assert.assertEquals(p.CanDelete(),false);
		Assert.assertNotEquals(p.IsSuperUser(), true);
	}
	
}
