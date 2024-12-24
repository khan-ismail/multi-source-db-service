package zerotoismail.com.datasourcelearningserviceorg.dto;

public class QueryRequestDto {
    String query;

    public QueryRequestDto() {}

    public QueryRequestDto(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
