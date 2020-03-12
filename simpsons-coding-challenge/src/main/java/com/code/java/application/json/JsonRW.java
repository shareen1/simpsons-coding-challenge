package com.code.java.application.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.code.java.application.bean.CharacterBean;
import com.code.java.application.bean.MyCache;

@Component
public class JsonRW {

	public static void loadChacheOnStart() {
		readCharacter();
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

	public static void readCharacter() {

		String filename = "src/main/resources/data/characters.json";
		// Iterate over employee array
		JSONArray characterList = readJsonFile(filename);
		characterList.forEach(ch -> parseCharacterObject((JSONObject) ch));

	}

	public static JSONArray readPhrases() {
		String filename = "src/main/resources/data/phrases.json";
		// Iterate over employee array
		JSONArray characterList = readJsonFile(filename);
		return characterList;
	}

	private static void parseCharacterObject(JSONObject ch) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(CharacterBean.class);
		context.refresh();
		CharacterBean characterbean = (CharacterBean) context.getBean(CharacterBean.class);

		// Get character object within list
		String id = (String) ch.get("_id");
		characterbean.setChId(id);
		Set<String> comments = parseComment(characterbean, id);
		characterbean.setComments(comments, id);
		characterbean.setComments(comments, id);
		// Get character first name
		String firstName = (String) ch.get("firstName");
		characterbean.setFirstName(firstName);

		// Get character last name
		String lastName = (String) ch.get("lastName");
		characterbean.setLastName(lastName);

		// Get employee picture URL name
		String pictureURL = (String) ch.get("picture");
		characterbean.setPictureURL(pictureURL);
		Long age = (Long) ch.get("age");
		characterbean.setAge(age);
		MyCache.getInstance().addToList(id, characterbean);
		context.close();
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
		InputStream is = null;
		OutputStream os = null;
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
			while ((count += destination.transferFrom(origin, count, size - count)) < size)
				;
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
