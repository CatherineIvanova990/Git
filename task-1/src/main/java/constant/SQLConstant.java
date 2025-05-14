package constant;

public class SQLConstant {

    public static final String createTable = """
                create table if not exists Users
                          (
                              id       serial primary key,
                              name     varchar(255),
                              last_name varchar(255),
                              age      smallint
                          );
                """;
    public static final String dropTable = """
                drop table if exists Users;
                """;
    public static final String saveUser = """
                insert into Users (name, last_name, age)
                values (?,?,?);
                """;
    public static final String removeUser = """
                delete from Users where id = ?;
                """;
    public static final String getUsers = """
                select * from Users;
                """;
    public static final String removeAllUsers = """
                delete from Users;
                """;

}
