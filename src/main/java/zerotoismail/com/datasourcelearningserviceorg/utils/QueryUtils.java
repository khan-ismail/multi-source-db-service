package zerotoismail.com.datasourcelearningserviceorg.utils;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import zerotoismail.com.datasourcelearningserviceorg.exception.QueryNotAllowedException;


public class QueryUtils {

    public static boolean validateQuery(String query) {
        try {
            Statement statement = CCJSqlParserUtil.parse(query);
            return statement instanceof Select;
        } catch (Exception e) {
            throw new QueryNotAllowedException("Invalid query, Only SELECT queries are permitted: " + e.getMessage());
        }
    }
}
