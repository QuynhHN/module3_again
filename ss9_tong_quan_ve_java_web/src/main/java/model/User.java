package model;

public class User {
    private int  id;
    private String useName;
    private String passwword;

    public User() {
    }

    public User(int id, String useName, String passwword) {
        this.id = id;
        this.useName = useName;
        this.passwword = passwword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getPasswword() {
        return passwword;
    }

    public void setPasswword(String passwword) {
        this.passwword = passwword;
    }
}
