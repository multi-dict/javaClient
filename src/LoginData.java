import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;



import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class LoginData {
	private static 	String loginpath;
	private String username;
	private String password;
	private String sessionToken;

	private static void SetLoginPath(String configPath) throws Exception{
		JSONObject temp = MyJsonFileParser.JsonFromFile(configPath);
		loginpath = temp.getString("loginpath");
		
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

	public boolean OldLogin() throws Exception {

		String urlpath = "http://5.159.235.37:5001/login";

		URL url = new URL(urlpath);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");
		urlConnection.setUseCaches(false);
		urlConnection.setConnectTimeout(10000);
		urlConnection.setReadTimeout(10000);
		urlConnection.setRequestProperty("Content-Type", "application/json");

		urlConnection.connect();

		// Create JSONObject here
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("username", this.username);
		jsonParam.put("password", this.password);

		DataOutputStream printout = new DataOutputStream(urlConnection.getOutputStream());
		printout.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
		printout.flush();
		printout.close();

		int responseCode = urlConnection.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + urlpath);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

		return false;

	}

	public int Login() throws Exception {
		try{
			LoginData.SetLoginPath("config.json");
		}
		catch (Exception ex){
			return 0;
		}
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("username", this.username);
		jsonParam.put("password", this.password);

		int statusCode = 0;

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPost request = new HttpPost(LoginData.loginpath);
			StringEntity params = new StringEntity(jsonParam.toString());
			request.addHeader("content-type", "application/json");
			request.setEntity(params);
				//handle response here
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
			}
		}
		catch (Exception ex) {
			// handle exception here
		} finally {
			httpClient.close();
		}
		return statusCode;
	}
}
