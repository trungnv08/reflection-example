package annotation;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JsonProcessorTest {

    @Test
    void testJsonSerialize() throws IllegalAccessException {
        class ABC {
            @JsonField("first_name")
            private String a;
            @JsonField("last_name")
            private String b;
            @JsonField("age")
            private int count = 1;
            private char gender = 'M';
        }

        ABC abc = new ABC();
        abc.a = "Trung";
        abc.b = "Nguyen";
        abc.count = 25;

        System.out.println(JsonProcessor.toJson(abc));

    }

    @Test
    void testPerson() {
        Person person = new Person();
        person.setName("Trung");
        person.setDateOfBirth(LocalDateTime.now());
        System.out.println(JsonProcessor.toJson(person));
//        System.out.println(JsonProcessor.toJsonWithoutModify(person));

    }
}