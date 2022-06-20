package cn.hhnail.backend;

import org.junit.jupiter.api.Test;

public class StringTests {
	public static void main(String[] args) {
		String str1 = "[\"id\":\"123\"]";
		System.out.println(str1);
	}

	@Test
	public void test01(){

		System.out.println(null + "");

	}


	@Test
	public void test02(){
		System.out.print(" test ".trim());
		System.out.println("test".trim());

		System.out.print(" test ");
		System.out.print("test".trim());
	}


}
