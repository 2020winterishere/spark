package cs246.project;

public class Client {

    private String name;
    private Contact contact;

public Client (String name, String phoneNumber, String comments){
    this.name = name;
    this.contact = new Contact(phoneNumber, comments);
}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
