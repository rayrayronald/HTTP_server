import java.io.Serializable;

public class Patient implements Serializable {
    public String name;
    public int phone;
    public int ID;

    public Patient(String name, int phone, int ID){
        this.phone = phone;
        this.ID = ID;
        this.name = name;

    }


}
