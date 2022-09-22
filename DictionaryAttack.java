import java.util.*;

public class DictionaryAttack {
	public static Scanner scanner = new Scanner(System.in);

	// 입력, 출력 예시는 코드 젤 하단에 기재됨
	public static void main(String[] args) {
		int passwordLength = scanner.nextInt();
		int numOfDictionaryWords = scanner.nextInt();
		String exposedPassword = scanner.next();
		List<String> dictionary = new LinkedList<>();

		for(int addCount=0; addCount<numOfDictionaryWords; addCount++){
			dictionary.add(scanner.next());
		}

		Collections.sort(dictionary);
		String expectedPassword = doDictionaryAttack(exposedPassword, passwordLength, dictionary);

		System.out.println(expectedPassword);
	}

	public static String doDictionaryAttack(String exposedPassword, int exposedPasswordLength, List<String> dictionary){
		/**
		 * exposedPassword 에서 dictionary 의 단어와 비교
		 * 비밀번호 후보군이 될 수 있는 단어들은 따로 보관
		 * 후보군에 저장된 단어를 하나씩 순회하여 exposedPassword 와 비교
		 * exposedPassword 가 비교할 후보군의 길이와 일치할 때 후보군 단어 반환
		 * 더 이상 비교할 단어가 없는 상태라면 빈 문자열 반환
		 * 위 2개 조건 불충족 시 "후보군 단어" + doDictionaryAttack() 반환
		 */

		List<String> matchedWords = new ArrayList<>();

		for (String dictWord : dictionary) {
			if(Objects.equals(dictWord, null)){
				continue;
			}
			if (!exposedPassword.contains("?")){
				return exposedPassword;
			}

			int dictLength = dictWord.length();

			if (checkWordMatches(exposedPassword, 0, dictWord)) {

				if (exposedPasswordLength == dictLength) {
					return dictWord;
				}

				matchedWords.add(dictWord);
			}
		}

		for(String matchWord : matchedWords){
			int dictIdx = dictionary.indexOf(matchWord);
			int nextCheckRangeStart = matchWord.length();
			int nextPasswordLength = exposedPasswordLength-matchWord.length();

			dictionary.set(dictIdx, null);

			String value = matchWord + doDictionaryAttack(exposedPassword.substring(nextCheckRangeStart), nextPasswordLength, dictionary);

			dictionary.set(dictIdx, matchWord);

			if(value.replace(" ", "").length() == exposedPassword.length()){
				return value;
			}
		}

		return "";
	}

	public static boolean checkWordMatches(String origin, int startIdx, String compare){
		/**
		 * 비교할 문자열은 숫자가 없는 영어임
		 * 비교할 문자열의 길이를 기준으로 한글자씩 비교
		 * 문자가 다른게 있다면 즉시 false 반환
		 * 반복문을 전부 수행했다면 true 반환
		 */

		int originLength = origin.length();
		int compareLength = compare.length();

		if(originLength < compareLength){
			return false;
		}

		for(int wordCnt=0; wordCnt<compare.length(); wordCnt++){
			char originCh = origin.charAt(startIdx + wordCnt);
			char compareCh = compare.charAt(wordCnt);
			if(originCh == '?'){
				continue;
			}
			if(originCh != compareCh){
				return false;
			}
		}

		return true;
	}
}

/**
 * 예시.
 * 입력
 * 40 40
 * z????dgrktn?x??z?t?h?fki?nbef?t??hf?j?xe
 * iuzdtthbfkiznbefh
 * rktnlxiuzdtthbfki
 * t
 * nbefhtukhfdj
 * ktnlxiuzdtthbfkiznbe
 * dtth
 * htukhfdjg
 * dj
 * bfkiznbefh
 * fd
 * hbfk
 * tnlxiuzdtthbfkiznbef
 * znbefhtukhfdjg
 * ugjedgrktnlxi
 * thbfkiznbef
 * fdjgxe
 * e
 * befh
 * x
 * grktnlxiuzdtthbfkizn
 * e
 * nlxiuzdtthbfkiznbe
 * jedgrktnlxi
 * uzdtth
 * uk
 * gjedg
 * jgxe
 * htuk
 * g
 * xiuzdtthbfkizn
 * jedgrktnlxiuzdtthbf
 * zn
 * x
 * xi
 * gjedgrktnlxiu
 * uzdtth
 * iz
 * zdtthbfkiznbefht
 * jgxe
 * efhtukh
 * 출력
 * zndjfdgrktnlxiuzdtthbfkienbefhtukhfdjexe
 */