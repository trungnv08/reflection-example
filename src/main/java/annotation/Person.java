package annotation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonField(value = "person")
public class Person {

    private String name;

    @JsonField("dob")
    private LocalDateTime dateOfBirth;
}
