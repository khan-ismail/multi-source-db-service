package zerotoismail.com.datasourcelearningserviceorg.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    Long id;
    String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
