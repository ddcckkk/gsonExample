package com.chinatele.domain;

import java.util.Arrays;

import com.google.gson.annotations.SerializedName;

public class Book {
	  private String[] authors;

	 // @SerializedName("isbn-10")
	  // 不用注解配了
	  private String isbn10;
	  //isbn-13, isbn13,isbn.13都能识别。
	  @SerializedName(value = "isbn-13", alternate = {"isbn13", "isbn.13"})
	  private String isbn13;
	  private String title;
	public String[] getAuthors() {
		return authors;
	}
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}
	public String getIsbn10() {
		return isbn10;
	}
	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}
	public String getIsbn13() {
		return isbn13;
	}
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Book [authors=" + Arrays.toString(authors) + ", isbn10=" + isbn10 + ", isbn13=" + isbn13 + ", title="
				+ title + "]";
	}
	  
}
