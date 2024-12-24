package zerotoismail.com.datasourcelearningserviceorg.utils;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import zerotoismail.com.datasourcelearningserviceorg.exception.QueryNotAllowedException;


public class QueryUtils {

    public static boolean validateQuery(String query) {
        try {
            Statement statement = CCJSqlParserUtil.parse(query);
            if (statement instanceof Select) {
                return true;
            }
            throw new QueryNotAllowedException("Query not allowed, Only SELECT queries are permitted: ");
        } catch (Exception e) {
            throw new RuntimeException("Syntax Invalid SQL query: " + e.getMessage());
        }
    }
}
