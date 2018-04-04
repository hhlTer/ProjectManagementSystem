package view;

import controler.main.JDBCStorage;
import controler.commands.SQLMaker;
import enumerated.TypeCRUD;

import java.util.HashMap;
import java.util.Map;

public class GetDataDialog {
    public GetDataDialog(){

    }

    private Map<TypeCRUD, Map<String, SQLMaker>> sqlMap;

    private JDBCStorage jdbcStorage; //TODO Delete

    void init(){//@todo ?????????????
        jdbcStorage = new JDBCStorage();
        initPrepareStatements();
    }

    private void initPrepareStatements(){//TODO ??????
        sqlMap = new HashMap<>();

        Map<String, SQLMaker> tableMap = new HashMap<>();
        sqlMap.put(TypeCRUD.CREATE, tableMap);
    }
}
