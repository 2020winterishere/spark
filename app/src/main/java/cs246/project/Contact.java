package cs246.project;

public class Contact {
    private String phone;
    private String auxInfo;

    public Contact(String phone, String auxInfo) {
        this.phone = phone;
        this.auxInfo = auxInfo;
    }

    public String getPhone() {
        return phone;
    }

    public String getAuxInfo() {
        return auxInfo;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAuxInfo(String auxInfo) {
        this.auxInfo = auxInfo;
    }
}