package uz.pdp.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull(message = "Email bo'sh bo'lisi mumkun emas")
    private String email;

    @NotNull(message = "password bo'sh bo'lisi mumkun emas")
    private String password;

}
