/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennd.registration;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class RegistrationCreateError implements Serializable{
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String fullNameLengthErr;
    private String confirmMatched;
    private String usernameIsExist;

    public RegistrationCreateError() {
    }

    public RegistrationCreateError(String usernameLengthErr, String passwordLengthErr, String fullNameLengthErr, String confirmMatched, String usernameIsExist) {
        this.usernameLengthErr = usernameLengthErr;
        this.passwordLengthErr = passwordLengthErr;
        this.fullNameLengthErr = fullNameLengthErr;
        this.confirmMatched = confirmMatched;
        this.usernameIsExist = usernameIsExist;
    }

    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    public String getConfirmMatched() {
        return confirmMatched;
    }

    public void setConfirmMatched(String confirmMatched) {
        this.confirmMatched = confirmMatched;
    }

    public String getUsernameIsExist() {
        return usernameIsExist;
    }

    public void setUsernameIsExist(String usernameIsExist) {
        this.usernameIsExist = usernameIsExist;
    }
       
}
