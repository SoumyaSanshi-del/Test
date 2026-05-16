package soumyasanshi.NormalExample;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SureNot {
	
	public void Maybe() throws IOException {
		//json to String
		String fole=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\soumyasanshi\\NormalExample\\Notsure.json"), StandardCharsets.UTF_8);
	
	//String to map
		ObjectMapper ob=new ObjectMapper();
		List<HashMap<String, String>> ol=ob.readValue(fole, new TypeReference<List<HashMap<String,String>>>(){});
	
	}

}
