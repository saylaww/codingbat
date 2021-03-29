package uz.pdp.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExampleDto {
    @NotNull(message = "Text bo'sh bo'lmasligi kerak")
    private String text;

    @NotNull(message = "Task bo'sh bo'lmasligi kerak")
    private Integer taskId;
}
