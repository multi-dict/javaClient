package main;
import org.json.JSONObject;

import models.LoginData;
import swing.LoginFrame;
import swing.MainFrame;

public class Application {
	public static String baseURL;
	public static LoginData user;
	public static MainFrame mf;
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {	
		int errorCode = 0;
		try{
			JSONObject temp = MyJsonFileParser.JsonFromFile("./config.json");
			Application.baseURL = temp.getString("baseURL");
		}
		catch (Exception ex){
			errorCode = 1;
			Application.baseURL= "5.159.235.37:5001"; 
			System.out.println("Error during reading config file and setting baseURL");
		}
		LoginFrame loginWindow = new LoginFrame(errorCode);
		loginWindow.show(true);
	}
}
