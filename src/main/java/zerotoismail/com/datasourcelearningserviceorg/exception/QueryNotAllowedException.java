package zerotoismail.com.datasourcelearningserviceorg.exception;

public class QueryNotAllowedException extends RuntimeException{
    public QueryNotAllowedException(String message) {
        super(message);
    }
}
