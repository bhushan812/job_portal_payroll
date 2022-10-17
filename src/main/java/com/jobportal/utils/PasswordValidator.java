package com.jobportal.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

	// digit + lowercase char + uppercase char + punctuation + symbol=no white space
	private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,50}$";

	private static final Pattern PATTERN = Pattern.compile(PASSWORD_PATTERN);

	public static boolean isValid(final String password) {
		Matcher matcher = PATTERN.matcher(password);
		return matcher.matches();
	}

	private static final String MAIL_PATTERN = "^\\S+@\\S+\\.\\S+$";
	private static final Pattern MAIL = Pattern.compile(MAIL_PATTERN);

	public static boolean isValidforEmail(final String email) {

		Matcher matcher = MAIL.matcher(email);

		return matcher.matches();
	}

	private static final String NUMBER_FORMAT = "^[1-9]\\d*$";
	private static final Pattern NUMBER = Pattern.compile(NUMBER_FORMAT);

	public static boolean isValidNumber(final String numberId) {
		Matcher matcher = NUMBER.matcher(numberId);

		return matcher.matches();
	}
}
