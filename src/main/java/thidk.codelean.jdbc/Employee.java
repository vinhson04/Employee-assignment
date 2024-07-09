package thidk.codelean.jdbc;

import java.sql.Date;

public class Employee {


    private int employee_id;
    private String employee_name;
    private String birthday;
    private String phone;
    private String email;



    public Employee(int employee_id, String employee_name, String birthday, String phone, String email) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
    }

    public Employee( String employee_name, String birthday, String phone, String email) {
        this.employee_name = employee_name;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee [employee_id=" + employee_id + ", employee_name=" + employee_name + ", birthday=" + birthday + ", phone=" + phone + ", email=" + email + "]";
    }

}
