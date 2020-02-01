package Bankomat.Model;

public class Client {
    private int ID;
    private String name;
    private String pinCode;
    private int personNr;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setPersonNr(int personNr) {
        this.personNr = personNr;
    }

    public int getPersonNr() {
        return personNr;
    }
}