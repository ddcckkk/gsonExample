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
		// ���͵�ʹ��   http://www.jianshu.com/p/e740196225a4
		Gson gson = new Gson();
		String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
		String[] strings = gson.fromJson(jsonArray, String[].class);
		List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>() {}.getType());
		System.out.println(Arrays.asList(stringList));
		System.out.println(stringList);
	}

	private void SupportPOJO() {
		//����POJO֧���Զ����л��������л�  
		Gson gson = new Gson();
		int i = gson.fromJson("100", int.class);              //100
		double d = gson.fromJson("\"99.99\"", double.class);  //99.99
		// �Ӳ���  \"���� ,ת���ַ���    �����Ǹ�99.99���ַ���
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
		person.setName("��ï");
		person.setAge(11);
		person.setHobby(new String[]{"eat","play","Ϲ��"});
		String json = gson.toJson(person);
		System.out.println("POJO���Ϳ���֧���Զ����л���\n"+json);
		String json1 = "{\"����\":\"��ï\",\"age\":11,\"hobby\":[\"eat\",\"play\",\"Ϲ��\"]}";
		Person fromJson = gson.fromJson(json, Person.class);
		Person fromJson1 = gson.fromJson(json1, Person.class);
		System.out.println("POJO���Ϳ���֧���Զ������л���\n"+fromJson);
		System.out.println("POJO���Ϳ���֧���Զ������л�1��\n"+fromJson1);
	}

	private void testSerialiseAndDeserialise() {
		//�ֶ��������л��ͷ����л���ʽ ��ע����Զ������л���ʽ���ʹ����û�г�ͻ�ģ������Ժ�Ϊ�˷�ֹ�ֶ�
		//�ж�����д��ʽ��������ע������ã������ú��³�ͻ��
		GsonBuilder gsonBuilder = new GsonBuilder();
		//����Exposeע�⣬��ע���������ö�Ӧ�ֶ��Ƿ����л����߷����л���Ĭ�϶���true
		gsonBuilder.excludeFieldsWithoutExposeAnnotation();
		//����Լ�д�����л���ʽ
	    gsonBuilder.registerTypeAdapter(Book.class, new BookSerialiser());
		 //�����ʽ����json
		  gsonBuilder.setPrettyPrinting();
		  Gson gson = gsonBuilder.create();

		  Book javaPuzzlers = new Book();
		  javaPuzzlers.setTitle("Java Puzzlers: Traps, Pitfalls, and Corner Cases");
		  javaPuzzlers.setIsbn10("032133678X");
		  javaPuzzlers.setIsbn13("978-0321336781");
		  javaPuzzlers.setAuthors(new String[] { "Joshua Bloch", "Neal Gafter" });

		  // Format to JSON
		  String json = gson.toJson(javaPuzzlers);
		  System.out.println("���л������\n"+json);
		  
		  // ��ʼ�����л�----------------------------------
		  gsonBuilder.registerTypeAdapter(Book.class, new BookDeserializer());
		  gson = gsonBuilder.create();

		  // The JSON data
		    // Parse JSON to Java
		    Book book = gson.fromJson(json, Book.class);
		    System.out.println("�����л������\n"+book);
	}

	private void isPrototype() {
		// ˵��create�����Ĳ��ǵ���ģʽ
		GsonBuilder gsonBuilder  = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		Gson gson1 = gsonBuilder.create();
		System.out.println(gson == gson1);
	}
}
