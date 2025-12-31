package az.banking.bankmanagementsystem.Security.model;



import lombok.Data;
//Bunu biz postmanda body ile gonderirik
@Data
public class UsernameAndPasswordAuthentication {

    private String Fincode;
    private String password;

}
