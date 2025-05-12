package DBConfig;

import org.postgresql.Driver;

public final class DataBaseConfig {
    public static final String DRIVER = "org.postgresql.Driver";
    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String USER = "postgres";
    public static final String PASS = "postgres";

    private DataBaseConfig() {
    }
}
