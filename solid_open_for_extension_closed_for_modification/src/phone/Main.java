package phone;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	public static void main(String args[]) {
		System.out.println("Enter which mobile to use\n"
				+ "1->Samsung\n"
				+ "2->Iphone\n"
				+ "3->Vivo");
		Phone phone = null;
		int opt = sc.nextInt();
		switch(opt) {
			case 1: phone = new Samsung("S12", "White");
				break;
			case 2: phone = new Iphone("iphone13", "black");
				break;
			case 3: phone = new Vivo("reno13", "Yellow");
				break;
			default:System.out.println("Enter correct option");
				break;
		}
		
	}
	public static void useMobile(Phone phone) {
		boolean loop = true;
		while(loop) {
			System.out.println("1->open camera\n"
					+ "2->download File\n"
					+ "3->update next version\n"
					+ "4->Switch off");
			int opt = sc.nextInt();
			switch (opt) {
			case 1: phone.openCamera();
				break;
			case 2:phone.downloadFile(1);
				break;
			case 3:phone.updateNextVersion();
				break;
			case 4:loop = false;
				System.out.println("phone switching off");
				break;
			default:
				break;
			}
		}
	}
}
