package az.banking.bankmanagementsystem.Security.model;



import lombok.Data;
//Bunu biz postmanda body ile gonderirik
@Data
public class UsernameAndPasswordAuthentication {

    private String username;
    private String password;

}
