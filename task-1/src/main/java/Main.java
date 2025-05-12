import model.User;
import service.UserServiceImpl;

import java.util.List;

public class Main {

    private static final UserServiceImpl service = new UserServiceImpl();

    public static void main(String[] args) {
        service.createUsersTable();

        service.saveUser("Ivan","Ivanov", (byte) 23);
        service.saveUser("Petr","Petrov", (byte) 45);
        service.saveUser("Sasha","Aleksandrov", (byte) 37);
        service.saveUser("Denis","Denisov", (byte) 29);

        List<User> allUsers = service.getAllUsers();
        for (User user : allUsers) {
            System.out.println(user);
        }

        service.cleanUsersTable();

        service.dropUsersTable();


        // реализуйте алгоритм здесь
    }
}
