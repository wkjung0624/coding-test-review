import java.util.Scanner;
import java.util.regex.Pattern;

public class RegisterValidation {
	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args)
	{
		/**
		 * 조건1. 아이디 3글자 이상, 20글자 이하 알파벳 소문, 숫자
		 * 조건2. 8글자 20글자 이하 알파벳 대/소, 숫자,특수기호, 각각 반드시 1개이상
		 * 조건3. 비밀번호, 비밀번호확인 이 일치해야함
		 */

		String id = scanner.next();
		String pw1 = scanner.next();
		String pw2 = scanner.next();

		if(validateRegister(id, pw1, pw2))
			System.out.println("pass");
		else
			System.out.println("fail");
	}
	public static boolean validateRegister(String username, String password, String passwordConfirm){
		return validateId(username) && validatePw(password) && password.equals(passwordConfirm);
	}
	public static boolean validateId(String username){
		String patternForIdCheck = "^[a-zA-Z]{1}[a-zA-Z0-9]{2,19}$";
		return Pattern.matches(patternForIdCheck, username);
	}
	public static boolean validatePw(String password){
		String patternForPwCheck = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$])[a-zA-Z0-9!@#$]{3,20}";
		return Pattern.matches(patternForPwCheck, password);
	}
}

