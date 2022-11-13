package cn.hhnail.backend;

public class ForLoopTests {

	public static void main(String[] args) {
		int count = 0;
		loopRemark: for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				count++;
				if(j == 3){
					break loopRemark;
				}
			}
		}
		System.out.println(count);

		int count2 = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) { // <==> for (int j = 0; j <= 3; j++)
				count2++;
				if(j == 3){
					break;
				}
			}
		}
		System.out.println(count2);

		int count3 = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j <= 3; j++) {
				count3++;
			}
		}
		System.out.println(count3);
	}


}
