package thidk.codelean.jdbc;

public class Feedback {

    private int id;
    private String Name;
    private String Phone;
    private String Email;
    private String Message;

    public Feedback(int id, String name, String phone, String email, String message) {
        this.id = id;
        this.Name = name;
        this.Phone = phone;
        this.Email = email;
        this.Message = message;
    }

    public Feedback(String name, String phone, String email, String message) {
        this.Name = name;
        this.Phone = phone;
        this.Email = email;
        this.Message = message;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return Name;}

    public void setName(String name) {this.Name = name;}

    public String getPhone() {return Phone;}

    public void setPhone(String phone) {this.Phone = phone;}

    public String getEmail() {return Email;}

    public void setEmail(String email) {this.Email = email;}

    public String getMessage() {return Message;}

    public void setMessage(String message) {this.Message = message;}


    @Override
    public String toString() {
        return "Feedback [id=" + id + ", Name=" + Name + ", Phone=" + Phone + ", Email=" + Email + ", Message=" + Message + "]";
    }
}
