package soumyasanshi.parameterFiles;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataFile {
	
	public List<HashMap<String, String>> getJsonDataTOFile() throws IOException {
		
		//Converting json to string
		String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\soumyasanshi\\parameterFiles\\PurchaseOrder.json"),StandardCharsets.UTF_8);
		
		//String to HashMap{need jackson databind dependency}
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
	}

}
