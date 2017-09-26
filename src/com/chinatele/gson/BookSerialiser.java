package com.chinatele.gson;

import java.lang.reflect.Type;

import com.chinatele.domain.Book;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class BookSerialiser implements JsonSerializer {
	@Override
	public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {
		 	Book book = (Book)src;
			JsonObject jsonObject = new JsonObject();
	        jsonObject.addProperty("title", book.getTitle());
	        jsonObject.addProperty("isbn-10", book.getIsbn10());
	        jsonObject.addProperty("isbn-13", book.getIsbn13());

	        JsonArray jsonAuthorsArray = new JsonArray();
	        for (String author : book.getAuthors()) {
	            JsonPrimitive jsonAuthor = new JsonPrimitive(author);
	            jsonAuthorsArray.add(jsonAuthor);
	        }
	        jsonObject.add("authors", jsonAuthorsArray);
	        return jsonObject;
	}
}
