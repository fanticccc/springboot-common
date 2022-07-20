package com.example.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo6 {
    private Boolean ValidatePwd(String passWord){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(passWord);
        return matcher.find();


    }
}
