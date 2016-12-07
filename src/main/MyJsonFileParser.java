package main;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;



public class MyJsonFileParser {
	
	public static JSONObject JsonFromFile(String filepath) throws Exception{
		return new JSONObject(readFile(filepath,StandardCharsets.UTF_8));
	}
	static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}
}
