package controler.commands;

import controler.JDBCStorage;
import enumerated.TypeCRUD;

public interface SQLMaker {
    void handlerCommand(JDBCStorage jdbcStorage, String tableName, String[] param);
}
