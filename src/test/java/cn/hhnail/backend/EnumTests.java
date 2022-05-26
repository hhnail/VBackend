package cn.hhnail.backend;

public class EnumTests {

	public static void main(String[] args) {
		Status status = Status.valueOf(Status.AGREE.toString());
		System.out.println(status);
		System.out.println(Status.AGREE.toString());
	}
}

enum Status {

	AGREE("1"),
	UNAGREE("2");

	private String status;

	private Status(String status) {
		this.status = status;
	}
}
