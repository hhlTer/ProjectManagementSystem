package view;

import controler.JDBCStorage;
import controler.commands.SQLMaker;
import enumerated.TypeCRUD;

import java.util.HashMap;
import java.util.Map;

public class GetDataDialog {
    public GetDataDialog(){

    }

    private Map<TypeCRUD, Map<String, SQLMaker>> sqlMap;

    JDBCStorage jdbcStorage;

    void init(){
        jdbcStorage = new JDBCStorage();
        initPrepareStatements();
    }

    void initPrepareStatements(){
        sqlMap = new HashMap<>();

        Map<String, SQLMaker> tableMap = new HashMap<>();
        sqlMap.put(TypeCRUD.CREATE, tableMap);
    }
}
