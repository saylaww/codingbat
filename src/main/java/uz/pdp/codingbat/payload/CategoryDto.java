package uz.pdp.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.codingbat.entity.Language;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    @NotNull(message = "Name bo'sh bo'lmasligi kerak")
    private String name;

    @NotNull(message = "Description bo'sh bo'lmasligi kerak")
    private String description;

    @NotNull(message = "Language bo'sh bo'lmasligi kerak")
    private List<Integer> language;
}
