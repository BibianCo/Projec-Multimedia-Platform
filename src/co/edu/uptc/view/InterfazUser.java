package co.edu.uptc.view;

import java.util.ArrayList;

public class InterfazUser {

}

    public static boolean emailValidation(String email) {

        ArrayList<String> listDominio = new ArrayList<>();
        listDominio.add("@gmail.com");
        listDominio.add("@uptc.edu.co");
        listDominio.add("@outlook.es");
        listDominio.add("@yahoo.com");

        for (String s : listDominio) {
            if (email.contains(s)) {
                int position = email.length() - s.length();
                String aux = email.substring(0, position);

                if (aux.contains("@") || aux.length() < 5) {
                    return false;
                } else {
                    return true;
                }
            }

        }
        return false;
    }

    public static boolean passwordValidation(String password) {

        if (password.length() > 3 && password.length() < 20) { // >3 <20
            if (!password.equals(password.toLowerCase())) { // min. una mayuscula
                if (!password.equals(password.toUpperCase())) { // min. una miniscula
                    if (password.matches(".*\\d.*\\d.*")) { // min. 2 numeros
                        return true;
                    }
                }
            }
        }
        return false;
    }
