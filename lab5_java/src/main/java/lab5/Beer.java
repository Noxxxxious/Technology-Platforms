package lab5;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
public class Beer {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private float alcoholContent;
}
