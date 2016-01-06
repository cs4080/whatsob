package cat.urv.deim.sob;

import cat.urv.deim.sob.dao.IKey;
import cat.urv.deim.sob.dao.IObject;

public class User implements IObject{

    private String username;
    private String userlname;
    private String phone;
    private String email;
    private String pwd;
    
    public User(String username, String userlname, String phone, String email, String pwd){
        this.username=username;
        this.userlname=userlname;
        this.phone=phone;
        this.email=email;
        this.pwd=pwd;
    }

    public String getUsername() {
        return fixNull(this.username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserlname() {
        return fixNull(this.userlname);
    }

    public void setUserlname(String userlname) {
        this.userlname = userlname;
    }

    public String getEmail() {
        return fixNull(this.email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return fixNull(this.phone);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd(){
        return fixNull(this.pwd);
    }
    
    public void setPwd(String pwd){
        this.pwd=pwd;
    }
    
    @Override
    public IKey getKey(){
        return new UserPk(this.username);
    }
    
    private String fixNull(String in) {
        return (in == null) ? "" : in;
    }
}
