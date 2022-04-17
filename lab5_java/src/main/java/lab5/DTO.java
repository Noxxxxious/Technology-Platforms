package lab5;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
public class DTO {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private float percentage;
}
