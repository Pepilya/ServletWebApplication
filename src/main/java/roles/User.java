package roles;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 2041275512219239990L;

    private String login;
    private String password;
    private String email;
    private String info;

    public User() {
        this.login = "";
        this.password = "";
        this.email = "";
        this.info = "";
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getInfo() { return info;}

    public String setLogin(String login) {
        return this.login = login;
    }

    public String setPassword(String password) {
        return this.password = password;
    }

    public String setEmail(String email) {
        return this.email = email;
    }

    public String setInfo(String info) { return this.info = info;}

}
