package bauwerk78.model;

import bauwerk78.tools.Statics;

public class Login implements Statics {

/*
    public Login() {
        getUserLogin();
    }

*/

    public String[] getUserLogin() {
        String[] returnValue = new String[2];
        returnValue[0] = Statics.requestInput("Enter login: ");
        returnValue[1] = Statics.requestInput("Enter password: ");
        return returnValue;
    }



}
