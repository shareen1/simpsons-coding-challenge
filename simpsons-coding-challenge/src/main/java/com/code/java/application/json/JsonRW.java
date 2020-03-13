package com.code.java.application.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import com.code.java.application.bean.CharacterBean;
import com.code.java.application.bean.MyCache;

@ComponentScan(basePackageClasses = {CharacterBean.class,MyCache.class})
@ComponentScan ({"com.code.java.application.bean", "com.code.java.application.service","com.code.java.application","com.code.java.application.controller","com.code.java.application.json"})
@Controller
public class JsonRW {

		private HashMap<String, CharacterBean> mylist= new HashMap<>();;

		public void loadChacheOnStart(MyCache mcache, CharacterBean chbean) {
		readCharacter(mcache);
	
		System.out.print("cache loaded");
	}

	public static JSONArray readJsonFile(String filename) {
		// JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonList = null;
		try (FileReader reader = new FileReader(filename)) {
			// Read JSON file
			JSONObject obj = (JSONObject) jsonParser.parse(reader);
			jsonList = (JSONArray) obj.get("data");
			// System.out.println(characterList);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonList;
	}

	public  void readCharacter(MyCache mcache) {

		String filename = "src/main/resources/data/characters.json";
		// Iterate over employee array
		JSONArray characterList = readJsonFile(filename);
		for(int i=0;i<characterList.size();i++) {
			
			JSONObject myObject = (JSONObject)characterList.get(i);
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
			context.register(CharacterBean.class);
			context.refresh();
			CharacterBean chbean = context.getBean(CharacterBean.class);
			parseCharacterObject( myObject,mcache,chbean );
			context.close();
		}
		
		/*for (Object object : characterList) {
			JSONObject jsonobject=(JSONObject) object;
			
			mylist=parseCharacterObject( jsonobject,mcache, );
		}*/
		

	}

	public static JSONArray readPhrases() {
		String filename = "src/main/resources/data/phrases.json";
		// Iterate over employee array
		JSONArray characterList = readJsonFile(filename);
		return characterList;
	}

	private  HashMap<String, CharacterBean> parseCharacterObject(JSONObject ch, MyCache mych, CharacterBean characterBean) {
		
		//MyCache mycahe = myCacheService.getMycahe();
		//AnnotationConfigApplicationContext context = myCacheService.getContext();
		//context.register(CharacterBean.class);
		//context.register(MyCache.class);
		//context.refresh();
		//CharacterBean characterbean = (CharacterBean) myCacheService.getChBean();
		//MyCache myCache = (MyCache) context.getBean(MyCache.class);
		// Get character object within mylist
		
		String id = (String) ch.get("_id");
		characterBean.setChId(id);
		Set<String> comments = parseComment(characterBean, id);
		characterBean.setComments(comments, id);
		characterBean.setComments(comments, id);
		// Get character first name
		String firstName = (String) ch.get("firstName");
		characterBean.setFirstName(firstName);

		// Get character last name
		String lastName = (String) ch.get("lastName");
		characterBean.setLastName(lastName);

		// Get employee picture URL name
		String pictureURL = (String) ch.get("picture");
		characterBean.setPictureURL(pictureURL);
		Long age = (Long) ch.get("age");
		characterBean.setAge(age);
		
		return mych.addToList(id, characterBean,true,null);
		
		
	}

	private static Set<String> parseComment(CharacterBean characterbean, String id) {
		JSONArray praselist = readPhrases();
		Set<String> comentlist = new HashSet<String>();
		praselist.forEach(ch -> addToCommentSet((JSONObject) ch, comentlist, id));
		return comentlist;
	}

	private static void addToCommentSet(JSONObject ch, Set<String> comentlist, String characterId) {
		String character = (String) ch.get("character");
		String phrase = (String) ch.get("phrase");
		if (characterId.equals(character)) {
			comentlist.add(phrase);
		}
	}

	public static String copyImageFile(String fromFile, String toFile) throws IOException {
		String path = "src/main/resources/static";

		File file = new File(path);
		String absolutePath = file.getAbsolutePath() + "\\";
		copyFile(new File(fromFile),new File(absolutePath + toFile));
		return absolutePath;

	}

	public static void copyFile(File sourceFile, File destFile) throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel origin = null;
		FileChannel destination = null;
		try {
			origin = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			long count = 0;
			long size = origin.size();
			while ((count += destination.transferFrom(origin, count, size - count)) < size);
		} finally {
			if (origin != null) {
				origin.close();
			}
			if (destination != null) {
				destination.close();
			}
		}

	}
}
