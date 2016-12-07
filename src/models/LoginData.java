package models;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import main.Application;

public class LoginData {
	private static String loginPath;
	private String username;
	private String password;
	private String sessionToken;
	private Permissions permissions;

	private static void SetLoginPath(){
		loginPath = "http://" + Application.baseURL + "/login";

	}

	public LoginData(String uname, String pswd) {
		this.username = uname;
		this.password = pswd;
	}

	public void SetSessionToken(String sid) {
		this.sessionToken = sid;
	}

	public void SetSessionToken(JSONObject sid) throws Exception {
		this.sessionToken = sid.getString("session_token");
	}

	public String GetSessionID() {
		return this.sessionToken;
	}

	public int Login() throws Exception {
		LoginData.SetLoginPath();

		JSONObject jsonParam = new JSONObject();
		jsonParam.put("username", this.username);
		jsonParam.put("password", this.password);

		int statusCode = 0;

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPost request = new HttpPost(LoginData.loginPath);
			StringEntity params = new StringEntity(jsonParam.toString());
			request.addHeader("content-type", "application/json");
			request.setEntity(params);
			// handle response here
			HttpResponse response = httpClient.execute(request);
			statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
				JSONObject jsonresult = new JSONObject(result.toString());
				this.SetSessionToken(jsonresult);
				this.permissions = new Permissions(jsonresult);
			}
		} catch (Exception ex) {
			// handle exception here
		} finally {
			httpClient.close();
		}
		return statusCode;
	}

	public Permissions GetPermissions() {
		return this.permissions;
	}
	public String GetName(){
		return this.username;
	}
}
