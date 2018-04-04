package controler.main;

import enumerated.TypeCRUD;
import enumerated.TypeTable;
import model.Company;
import model.Customer;
import model.Developer;
import model.Projects;

import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * - init database (in constructor - 2 methods: initDB() & initConnection())
 * - init HashMap (
 *        #prepareStatementMap ) with all PrepareStations
 *        for get correct prepareStation example:
 *                PreparedStatement ps = prepareStatementsMap.get(TypeTable.companies).get(TypeCRUD.CREATE);
 *
 * public methods: only getter Map(!) //TODO: may be realize query to databases in this class
 *                                    //TODO: and call him from SQLMaker
 *                                    //TODO: OR extends JDBCStorage in SQLMaker`s
 */


public class JDBCStorage {
    public JDBCStorage() {
        initDB();
        initConnection();
        initPrepareStatements();
    }

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
     * prepare statements CRUD realization
     */
    //main Map with statements
    private Map<TypeTable, Map<TypeCRUD, PreparedStatement>> prepareStatementsMap;
    public Map<TypeTable, Map<TypeCRUD, PreparedStatement>> getPrepareStatementsMap() {
        return prepareStatementsMap;
    }


    //TODO: drop possible (all ps fields ). But can be used in debug. And convenient
    private PreparedStatement prepareInsertStatement; //Create
    private PreparedStatement prepareSelectStatement; //Read
    private PreparedStatement prepareSelectByIdStatement; //ReadById
    private PreparedStatement prepareUpdateStatement; //Update
    private PreparedStatement prepareDeleteStatement; //Delete


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
     * method for init prepare statements
     * <p>
     * prepareInsertStatement; //Create
     * prepareSelectStatement; //Read
     * prepareSelectByIdStatement; //ReadById
     * prepareUpdateStatement; //Update
     * prepareDeleteStatement; //Delete
     *
     * @param typeTable - name of mutable table
     * @param typeCRUD  - type of query (SELECT, DELETE, READ, UPDATE, READ_BY_ID)
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


//----------------------------------------- init Map realization -------------------------------
    /**
     * init Map with allPrepareStatement, for all tables, who exist in TypeCRUD
     *          (in accordance, must be present class in model package,
     *          with fields, getter String[] param and @Override toString() method)
     *
     * #1 init prepareStatementsMap:
     *      #void initPrepareStatements()
     *    - adds to HashMap prepStstemets call:
     * #2 #getPrepareStatement(TypeTable typeTable, TypeCRUD typeCRUD)
     *    - determines and case correct prepareStatement for all query to all tables
     *    -> accepts TypeTable and TypeCRUD
     *    -> call and return:
     *
     *    one of specify getters
     * #3 #getPrepareInsert/Select/Update///) and call:
     *    one of specify methods:
     *
     * #4 #private void initReadStatements(String tableName, String[] param)
     *    -> accept tableName
     *    -> init appropriate PrepareStatement field
     *    TODO: 03.04.18 Make HashMaps with mtm coherence?...
     *   
     */
    
    //================1======================
    private void initPrepareStatements(){
        prepareStatementsMap = new HashMap<>();
        Map<TypeCRUD, PreparedStatement> tempMap;
        for (int i = 0; i < TypeTable.values().length; i++) {
            tempMap = new HashMap<>();
            for (int j = 0; j < TypeCRUD.values().length; j++) {
                tempMap.put(TypeCRUD.values()[j],
                        getPrepareStatement(TypeTable.values()[i], TypeCRUD.values()[j]));
            }
            prepareStatementsMap.put(TypeTable.values()[i], tempMap);
        }
    }

    //================2======================
    private PreparedStatement getPrepareStatement(TypeTable typeTable, TypeCRUD typeCRUD){
        switch (typeCRUD){
            case UPDATE: return getPrepareUpdateStatement(typeTable);
            case CREATE: return getPrepareInsertStatement(typeTable);
            case READ_BY_ID: return getPrepareSelectByIdStatement(typeTable);
            case DELETE: return getPrepareDeleteStatement(typeTable);
            case READ: return getPrepareSelectStatement(typeTable);
        }
        return null;
    }

    //================3======================
    private PreparedStatement getPrepareInsertStatement(TypeTable tableName) {
        initPrepareStatements(tableName, TypeCRUD.CREATE);
        return prepareInsertStatement;
    }
    private PreparedStatement getPrepareSelectStatement(TypeTable tableName) {
        initPrepareStatements(tableName, TypeCRUD.READ);
        return prepareSelectStatement;
    }
    private PreparedStatement getPrepareSelectByIdStatement(TypeTable tableName) {
        initPrepareStatements(tableName, TypeCRUD.READ_BY_ID);
        return prepareSelectByIdStatement;
    }
    private PreparedStatement getPrepareUpdateStatement(TypeTable tableName) {
        initPrepareStatements(tableName, TypeCRUD.UPDATE);
        return prepareUpdateStatement;
    }
    private PreparedStatement getPrepareDeleteStatement(TypeTable tableName) {
        initPrepareStatements(tableName, TypeCRUD.DELETE);
        return prepareDeleteStatement;
    }

    private void initPrepareStatements(TypeTable typeTable, TypeCRUD typeCRUD) {
        String[] param = getParamTable(typeTable); //get fields array of current table
        if (typeCRUD == TypeCRUD.CREATE) initCreateStatements(typeTable.name(), param);
        if (typeCRUD == TypeCRUD.READ) initReadStatements(typeTable.name(), param);
        if (typeCRUD == TypeCRUD.READ_BY_ID) initReadByIdStatements(typeTable.name(), param);
        if (typeCRUD == TypeCRUD.UPDATE) initUpdateStatements(typeTable.name(), param);
        if (typeCRUD == TypeCRUD.DELETE) initDeleteStatements(typeTable.name());
    }

    //================4======================


    private void initReadStatements(String tableName, String[] param) {
        String s = param[0].equals("*") ? param[0] :
                Arrays.stream(param)
                        .collect(Collectors.joining(","));
        String sql = String.format("SELECT %s FROM %s", s, tableName);
        initStatement(sql, TypeCRUD.READ);
    }

    private void initReadByIdStatements(String tableName, String[] param) {
        String s = param[0].equals("*") ? param[0] :
                Arrays.stream(param)
                        .limit(param.length)
                        .collect(Collectors.joining(","));
        String sql = String.format("SELECT %s FROM %s WHERE id = ?", s, tableName);
        initStatement(sql, TypeCRUD.READ_BY_ID);
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
                "UPDATE INTO %s(%s) VALUES (%s) WHERE id = ?", tableName, s, q);
        initStatement(sql, TypeCRUD.UPDATE);
    }

    private void initDeleteStatements(String tableName) {
        String sql = String.format(
                "DELETE FROM %s WHERE id = ?", tableName);
        initStatement(sql, TypeCRUD.DELETE);
    }

    //////////////////////////////////////////////////////
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
                case READ_BY_ID:
                    prepareSelectByIdStatement = connection.prepareStatement(sql);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param typeTable - table name init
     * @return field of table (in array of String). Used in initPrepareStation(TypeTable, TypeCRUD) method;
     */
    private String[] getParamTable(TypeTable typeTable){
        switch (typeTable){
            case developers:
                return new Developer().getParam();
            case companies:
                return new Company().getParam();
            case projects:
                return new Projects().getParam();
            case customers:
                return new Customer().getParam();
            case skills:
                return new Developer.Skill().getParam();
            default:
                return null;
        }
    }

}