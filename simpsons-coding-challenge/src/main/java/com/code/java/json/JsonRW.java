package com.code.java.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

	// context.register(CharacterBean.class);
	// context.refresh();

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

	public void readCharacter() {

		String filename = "src/main/resources/data/characters.json";
		// Iterate over employee array
		JSONArray characterList = readJsonFile(filename);
		characterList.forEach(ch -> parseCharacterObject((JSONObject) ch));

	}

	public static JSONArray readPhrases() {
		String filename = "src/main/resources/data/phrases.json";
		// Iterate over employee array
		JSONArray characterList = readJsonFile(filename);
		// characterList.forEach(ch -> addComment((JSONObject) ch));
		return characterList;
	}

	private Object addComment(JSONObject ch) {
		// TODO Auto-generated method stub
		return null;
	}

	private static void parseCharacterObject(JSONObject ch) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(CharacterBean.class);
		context.refresh();
		CharacterBean characterbean = (CharacterBean) context.getBean(CharacterBean.class);

		// Get character object within list
		String id = (String) ch.get("_id");
		characterbean.setId(id);
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
		MyCache.addToList(id, characterbean);
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
		if (characterId == character) {
			comentlist.add(phrase);
		}
	}

	public static void main(String[] args) {
		// readJsonFile();

	}

}
