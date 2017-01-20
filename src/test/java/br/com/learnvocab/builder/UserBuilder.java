package br.com.learnvocab.builder;


import br.com.learnvocab.entity.Role;
import br.com.learnvocab.entity.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 */
public class UserBuilder {

    private List<User> users = new ArrayList<>();

    private UserBuilder(User user) {
        users.add(user);
    }

    public static UserBuilder newUser(String name, String email, String password, List<Role> roles) {
        User user = create(name, email, password, roles);
        return new UserBuilder(user);
    }

    private static User create(String name, String email, String password, List<Role> roles) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roles);
        user.setDateRegister(Calendar.getInstance());        
        return user;
    }
     
    public User buildOne() {
        return users.get(0);
    }

    public List<User> buildAll() {
        return users;
    }
}
