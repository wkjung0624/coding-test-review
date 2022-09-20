import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DuplicatedTicket {

	public static Scanner scanner =  new Scanner(System.in);
	public static void main(String[] args) {
		/**
		 * 조건 1. 회원번호는 고유값, 중복된 회원번호는 없어야함
		 * 조건 2. 같은 회원권이 2개 이상 존재하면 해당 번호는 제외함
		 */

		int numOfTickets = scanner.nextInt();
		List<Integer> values = new LinkedList<>();

		for(int i=0; i<numOfTickets; i++)
			values.add(scanner.nextInt());

		Collections.sort(values);

		int checkIdx = 0;
		while(checkIdx < values.size()){
			int checkVal = values.get(checkIdx);
			int firstIdx = values.indexOf(checkVal);
			int lastIdx = values.lastIndexOf(checkVal);

			if(firstIdx != lastIdx){
				for(int removeCnt=0; removeCnt<=lastIdx-firstIdx; removeCnt++) {
					values.remove(firstIdx);
				}
			} else {
				checkIdx++;
			}
		}

		for (Integer value : values)
			System.out.print(value + " ");
	}
}
