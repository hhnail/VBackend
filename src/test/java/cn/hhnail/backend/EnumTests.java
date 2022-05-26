package cn.hhnail.backend;

import org.junit.jupiter.api.Test;

public class EnumTests {

	public static void main(String[] args) {
		Status status = Status.valueOf(Status.AGREE.toString());
		System.out.println(status);
		System.out.println(Status.AGREE.toString());

	}

	@Test
	public void test01(){
		for (Status value : Status.values()) {
			System.out.println(value.equals("AGREE"));
			System.out.println(value.name());
			System.out.println(value.getStatus());
		}
	}
}

enum Status {

	AGREE("1"),
	UNAGREE("2");

	private String status;

	private Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
