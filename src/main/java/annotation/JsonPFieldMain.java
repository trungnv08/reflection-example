package annotation;

import java.time.LocalDateTime;

public class JsonPFieldMain {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("Trung");
        person.setDateOfBirth(LocalDateTime.now());
        System.out.println(JsonProcessor.toJson(person));
        System.out.println(JsonProcessor.toJsonWithoutModify(person));
    }
}
