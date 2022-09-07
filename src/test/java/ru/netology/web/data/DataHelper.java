package ru.netology.web.data;

public class DataHelper {
    public static class AuthInfo{
        private String login;
        private String password;

        public AuthInfo() {
            this.login = "vasya";
            this.password = "qwerty123";
        }

        public AuthInfo(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

    public static class VerificationInfo{
        private String code;

        public VerificationInfo() {
            this.code = "12345";
        }

        public VerificationInfo(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public static class CardInfo{
        private int number;
        private int count;
    }
}
