package com.chinatele.gson;

import java.lang.reflect.Type;

import com.chinatele.domain.Book;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class BookDeserializer implements JsonDeserializer<Book> {

	  @Override
	  public Book deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
	      throws JsonParseException {
	    JsonObject jsonObject = json.getAsJsonObject();

	    JsonElement jsonTitle = jsonObject.get("title");
	    String title = jsonTitle.getAsString();

	    String isbn10 = jsonObject.get("isbn-10").getAsString();
	    String isbn13 = jsonObject.get("isbn-13").getAsString();

	    JsonArray jsonAuthorsArray = jsonObject.get("authors").getAsJsonArray();
	    String[] authors = new String[jsonAuthorsArray.size()];
	    for (int i = 0; i < authors.length; i++) {
	      JsonElement jsonAuthor = jsonAuthorsArray.get(i);
	      authors[i] = jsonAuthor.getAsString();
	    }

	    Book book = new Book();
	    book.setTitle(title);
	    book.setIsbn10(isbn10);
	    book.setIsbn13(isbn13);
	    book.setAuthors(authors);
	    return book;
	  }
	}
