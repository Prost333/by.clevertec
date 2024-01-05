package by.clevertec.Entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;


import javax.validation.constraints.NotNull;
import java.util.UUID;


@Setter
@Getter
@Data
@ToString
@FieldNameConstants
public class Entity {

    public String id;

    @NotNull
    public String name;

    public String surname;
    @NotNull
    public String login;
    @NotNull
    public String password;
    public Entity() {
        this.id = UUID.randomUUID().toString();
    }

    public Entity(String id, String name, String surname, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }
}
