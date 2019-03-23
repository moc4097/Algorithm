package com.lanqiao.bitOperator;

import java.util.Scanner;

/**
 * 0~1间浮点实数的二进制表示
 * @author Moc
 *
 */
public class Demo06 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		double n = scn.nextDouble();
		StringBuilder sb = new StringBuilder("0.");
		while(n > 0) {
			n *= 2;
			if (n >= 1) {
				sb.append("1");
				n -= 1;
			}else {
				sb.append("0");
			}
			if (sb.length() > 34) {
				System.out.println("ERROR");
				break;
			}
		}
		System.out.println(sb.toString());
	}
}
