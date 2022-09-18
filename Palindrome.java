public class Palindrome {
	public static void main(String[] args) {
		String[] words = new String[]{"abcdcbafg", "bcdedade", "abacbdbc", "abba", "qeasdfdsati"};	 // expect 7, 5, 5

		for(String maybePalindromeStr : words) {
			int pLength = getPalindromeLengthFromStr(maybePalindromeStr);

			System.out.println(maybePalindromeStr + ":" + pLength);
		}
	}

	// 펠린드롬은 중점을 기준으로 서로 대칭 구조가 되는 문자열을 말한다.
	// 1. 검사할 문자열을 왼쪽에서부터 오른쪽으로 한 글자씩 순회한다. (A 라고 가정한다)
	// 2. 현재 순회중인 한 글자와 해당 문자열의 오른쪽 끝 범위부터 "펠린드롬" 수인지 검사하고, 오른쪽 범위를 1씩 좁혀간다. (B 라고 가정한다)
	//    검사 범위를 점점 좁혀가는 이유는 나올 수 있는 가장 큰 수부터 검사하여 효율성을 높이기 위함이다.
	// 3. "펠린드롬" 문자열 발견 시 해당 문자열의 길이를 저장한다. 만약 다음 발견될 팰린드롬 기댓값보다 현재 발견된 값이 낮을 경우
	//    현재 저장된 값을 반환한다.
	//    A = {x | 0 <= x < str.length()}, B = {x | a + 1 < x < str.length()} 일떄, a in A
	//    다음 발견될 펠린드롬값의 최대 길이는 str.length() - B - A 이기 때문이다.
	// 4. 3번에서 반환하지 않은 경우 다시 1번으로 돌아가서 반복
	public static int getPalindromeLengthFromStr(String word){

		int result = 0;

		for(int a=0; a<word.length(); a++){
			for(int b=word.length()-1; b>a; b--){

				int currentWordLength = b - a;

				if(currentWordLength < result) break;

				boolean isPalindrome = true;
				int numOfCenterIndex = (b-a)/2;

				for(int c=0; c<=numOfCenterIndex; c++){
					char leftCompCh = word.charAt(a+c);
					char rightCompCh = word.charAt(b-c);
					if(leftCompCh != rightCompCh){
						isPalindrome = false;
						break;
					}
				}

				if(isPalindrome) result = b-a+1;

				boolean willBiggerThanFuturePalindromeLength = word.length()-(a+1) <= result;

				if(willBiggerThanFuturePalindromeLength)
					return result;

				if(isPalindrome) break;
			}
		}

		return result;
	}
}
