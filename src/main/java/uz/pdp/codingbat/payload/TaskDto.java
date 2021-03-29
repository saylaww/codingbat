package uz.pdp.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    @NotNull(message = "Name bo'sh bo'lmasligi kerak")
    private String name;

    @NotNull(message = "Text bo'sh bo'lmasligi kerak")
    private String text;

    @NotNull(message = "Solution bo'sh bo'lmasligi kerak")
    private String solution;

    @NotNull(message = "Hint bo'sh bo'lmasligi kerak")
    private String hint;

    @NotNull(message = "Method bo'sh bo'lmasligi kerak")
    private String method;

    @NotNull(message = "Language bo'sh bo'lmasligi kerak")
    private String language;

}
