

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Activity13 {

	public static void main(String[] args) {

		Scanner scan=new Scanner(System.in);
		List<Integer>numArr=new ArrayList<>();
		Random indexGen=new Random();
		System.out.println("Enter the number of list ");
		System.out.println("Enter a EOL or any non-integer character has to stop :");
		while(scan.hasNextInt()) {
			numArr.add(scan.nextInt());
		
		}
		System.out.println(numArr.size());
		int generatedIndex=indexGen.nextInt(numArr.size());
		System.out.println("Random index generated:"  +generatedIndex);
		System.out.println("The number at generated index is: "  +numArr.get(generatedIndex));
		
		
	

	}

}