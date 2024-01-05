package by.clevertec.Dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Getter
@Setter
@ToString
public class EntityDto {

    private String id;

    @NotNull

    private String name;

    @NotNull

    private String surname;

    @NotNull

    private String login;

    @NotNull

    private String password;
}
