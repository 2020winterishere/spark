package cs246.project;

public class Client {

    private String name;
    private String phoneNumber;
    private String comments;

public Client (String name, String phoneNumber, String comments){
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.comments = comments;
}

public  void addExclamation(String name){
    System.out.printf(name + "!");
}




}
