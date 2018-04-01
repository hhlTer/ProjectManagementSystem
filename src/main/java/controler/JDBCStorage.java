package controler;

import enumerated.TypeCRUD;
import model.Developer;

import java.sql.*;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JDBCStorage {
    /**
     * fields with data for init connection to database "it_industry"
     */
    private final String SERVER_PATCH = "localhost:3306";
    private final String DB_NAME = "it_industry";
    private final String DB_LOGIN = "root";
    private final String DB_PASSWORD = "cttcmy";
    private final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    private Connection connection;
    private Statement statement;

    /**
     * prepare statements CRUD
     */
    private Map<TypeCRUD, Map<String, PreparedStatement>> prepareStatementMap;
    public Map<TypeCRUD, Map<String, PreparedStatement>> getPrepareStatementMap() {
        return prepareStatementMap;
    }


    private PreparedStatement prepareInsertStatement; //Create
    private PreparedStatement prepareSelectStatement; //Read
    private PreparedStatement prepareUpdateStatement; //Update
    private PreparedStatement prepareDeleteStatement; //Delete

    public PreparedStatement getPrepareInsertStatement() {
        return prepareInsertStatement;
    }
    public PreparedStatement getPrepareSelectStatement() {
        return prepareSelectStatement;
    }
    public PreparedStatement getPrepareUpdateStatement() {
        return prepareUpdateStatement;
    }
    public PreparedStatement getPrepareDeleteStatement() {
        return prepareDeleteStatement;
    }

    /**
     * constructor with initialization
     * initDB() -
     */
    public JDBCStorage() {
        initDB();
        initConnection();
        initPrepareStatements();
    }

    private void initConnection() {
        String connectionURI = String.format("jdbc:mysql://%s/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", SERVER_PATCH, DB_NAME);
        try {
            connection = DriverManager.getConnection(connectionURI, DB_LOGIN, DB_PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initDB() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * // ВИДАЛИТИ
     * default initialization all prepare statements - на видалення
     * <p>
     * prepareInsertStatement; //Create
     * prepareSelectStatement; //Read
     * prepareUpdateStatement; //Update
     * prepareDeleteStatement; //Delete
     */

    private void initPrepareStatements() {
        try {
            prepareInsertStatement = connection.prepareStatement(
                    "INSERT  INTO developers(id, first_name, age, sex, salary) VALUES (?, ?, ?, ?, ?)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * method for init prepare statements
     * <p>
     * prepareInsertStatement; //Create
     * prepareSelectStatement; //Read
     * prepareUpdateStatement; //Update
     * prepareDeleteStatement; //Delete
     *
     * @param tableName - name of mutable table
     * @param param     - data with current names of fields in table
     * @param typeCRUD  - type of query (SELECT, DELETE, READ, UPDATE)
     *                  <p>
     *                  method @initPrepareStatement(String tableName, String[] param, TypeCRUD typeCRUD)
     *                  init query, using TypeCRUD param,
     *                  call correct method (
     * @initReadStatements/initCreateStatements/initUpdateStatements/initDeleteStatements) and get him params @tableName adn @param
     * for CREATE (INSERT), READ (SELECT), UPDATE queries param is String[] value with names of table fields
     * methods initCreateStatement() & initUpdateStatement() used
     * @countQ(int count) method for build "?, ?, ?..." string and put her in appropriate PrepareStatement field;
     * methods (initReadStatements/initCreateStatements/initUpdateStatements/initDeleteStatements) prepare sql query
     * and call generally
     * @initStatement(String sql, TypeCRUD typeCRUD) method to simplify code and
     * localize try/catch block
     */

    private void initPrepareStatements(String tableName, String[] param, TypeCRUD typeCRUD) {
        if (typeCRUD == TypeCRUD.CREATE) initCreateStatements(tableName, param);
        if (typeCRUD == TypeCRUD.READ) initReadeStatements(tableName, param);
        if (typeCRUD == TypeCRUD.UPDATE) initUpdateStatements(tableName, param);
        if (typeCRUD == TypeCRUD.DELETE) initDeleteStatements(tableName);
    }

    private void initReadeStatements(String tableName, String[] param) {
        String s = param[0].equals("*") ? param[0] :
                Arrays.stream(param)
                        .collect(Collectors.joining(","));
        String sql = String.format("SELECT %s FROM %s", s, tableName);
        initStatement(sql, TypeCRUD.READ);
    }

    private void initCreateStatements(String tableName, String[] param) {
        String s = Arrays.stream(param)
                .collect(Collectors.joining(","));
        String q = countQ(param.length);
        String sql = String.format(
                "INSERT INTO %s(%s) VALUES (%s)", tableName, s, q);
        initStatement(sql, TypeCRUD.CREATE);
    }

    private void initUpdateStatements(String tableName, String[] param) {
        String s = Arrays.stream(param)
                .collect(Collectors.joining(","));
        String q = countQ(param.length);
        String sql = String.format(
                "UPDATE INTO %s(%s) VALUES (%s)", tableName, s, q);
        initStatement(sql, TypeCRUD.UPDATE);
    }

    private void initDeleteStatements(String tableName) {
        String sql = String.format(
                "DELETE FROM %s WHERE id = ?", tableName);
        initStatement(sql, TypeCRUD.DELETE);
    }

    private String countQ(int count) {
        return Stream.iterate(0, i -> 0)
                .map(i -> "?")
                .limit(count)
                .collect(Collectors.joining(","));
    }


    private void initStatement(String sql, TypeCRUD typeCRUD) {
        try {
            switch (typeCRUD) {
                case READ:
                    prepareSelectStatement = connection.prepareStatement(sql);
                    break;
                case CREATE:
                    prepareInsertStatement = connection.prepareStatement(sql);
                    break;
                case DELETE:
                    prepareDeleteStatement = connection.prepareStatement(sql);
                    break;
                case UPDATE:
                    prepareUpdateStatement = connection.prepareStatement(sql);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}