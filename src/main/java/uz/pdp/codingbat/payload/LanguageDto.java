package uz.pdp.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageDto {

    @NotNull(message = "Name bo'sh bo'lmasligi kerak")
    private String name;
}
