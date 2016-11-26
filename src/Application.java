import org.json.JSONObject;

public class Application {
	public static String baseURL;
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {	
		int errorCode = 0;
		try{
			JSONObject temp = MyJsonFileParser.JsonFromFile("config.json");
			Application.baseURL = temp.getString("baseURL");
		}
		catch (Exception ex){
			errorCode = 1;
			System.out.println("Error during reading config file and setting baseURL");
			return;
		}
		LoginFrame loginWindow = new LoginFrame(errorCode);
		loginWindow.show(true);
	}
}
