package com.chinatele.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.chinatele.domain.Book;
import com.chinatele.domain.Person;
import com.chinatele.gson.BookDeserializer;
import com.chinatele.gson.BookSerialiser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Test1 {
	// http://www.jianshu.com/p/fc5c9cdf3aab
	@Test
	public void TestDemo1() {
		//isPrototype();
		//testSerialiseAndDeserialise();
		SupportPOJO();
		//useOfGeneric();
	}

	
	private void useOfGeneric() {
		// 泛型的使用   http://www.jianshu.com/p/e740196225a4
		Gson gson = new Gson();
		String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
		String[] strings = gson.fromJson(jsonArray, String[].class);
		List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>() {}.getType());
		System.out.println(Arrays.asList(stringList));
		System.out.println(stringList);
	}

	private void SupportPOJO() {
		//对于POJO支持自动序列化，反序列化  
		Gson gson = new Gson();
		int i = gson.fromJson("100", int.class);              //100
		double d = gson.fromJson("\"99.99\"", double.class);  //99.99
		// 加不加  \"都行 ,转义字符啊    上面那个99.99是字符串
		boolean b = gson.fromJson("true", boolean.class);     // true
		String str = gson.fromJson("String", String.class);   // String
		String str1 = gson.fromJson("\"String\"", String.class);   // String
		System.out.println(i);
		System.out.println(d);
		System.out.println(b);
		System.out.println(str);
		System.out.println(str1);
		String jsonNumber = gson.toJson(100);       // 100
		String jsonBoolean = gson.toJson(false);    // false
		String jsonString = gson.toJson("String"); //"String"
		
		Person person = new Person();
		person.setName("王茂");
		person.setAge(11);
		person.setHobby(new String[]{"eat","play","瞎玩"});
		String json = gson.toJson(person);
		System.out.println("POJO类型可以支持自动序列化：\n"+json);
		String json1 = "{\"名字\":\"王茂\",\"age\":11,\"hobby\":[\"eat\",\"play\",\"瞎玩\"]}";
		Person fromJson = gson.fromJson(json, Person.class);
		Person fromJson1 = gson.fromJson(json1, Person.class);
		System.out.println("POJO类型可以支持自动反序列化：\n"+fromJson);
		System.out.println("POJO类型可以支持自动反序列化1：\n"+fromJson1);
	}

	private void testSerialiseAndDeserialise() {
		//手动设置序列化和反序列化格式 ，注解和自定义序列化格式混合使用是没有冲突的，所以以后为了防止字段
		//有多种书写格式，可以在注解里配好，而不用害怕冲突。
		GsonBuilder gsonBuilder = new GsonBuilder();
		//启用Expose注解，此注解用于配置对应字段是否序列化或者反序列化，默认都是true
		gsonBuilder.excludeFieldsWithoutExposeAnnotation();
		//添加自己写的序列化格式
	    gsonBuilder.registerTypeAdapter(Book.class, new BookSerialiser());
		 //输出格式化的json
		  gsonBuilder.setPrettyPrinting();
		  Gson gson = gsonBuilder.create();

		  Book javaPuzzlers = new Book();
		  javaPuzzlers.setTitle("Java Puzzlers: Traps, Pitfalls, and Corner Cases");
		  javaPuzzlers.setIsbn10("032133678X");
		  javaPuzzlers.setIsbn13("978-0321336781");
		  javaPuzzlers.setAuthors(new String[] { "Joshua Bloch", "Neal Gafter" });

		  // Format to JSON
		  String json = gson.toJson(javaPuzzlers);
		  System.out.println("序列化结果：\n"+json);
		  
		  // 开始反序列化----------------------------------
		  gsonBuilder.registerTypeAdapter(Book.class, new BookDeserializer());
		  gson = gsonBuilder.create();

		  // The JSON data
		    // Parse JSON to Java
		    Book book = gson.fromJson(json, Book.class);
		    System.out.println("反序列化结果：\n"+book);
	}

	private void isPrototype() {
		// 说明create出来的不是单例模式
		GsonBuilder gsonBuilder  = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		Gson gson1 = gsonBuilder.create();
		System.out.println(gson == gson1);
	}
}
