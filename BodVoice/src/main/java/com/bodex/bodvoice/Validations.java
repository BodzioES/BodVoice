package com.bodex.bodvoice;

public class Validations {

    public static boolean isEmailValid(String email) {
        String emailRegex = "^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,4}$";
        return email.matches(emailRegex);
    }

    public static boolean isPasswordValid(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(passwordRegex);
    }
}
