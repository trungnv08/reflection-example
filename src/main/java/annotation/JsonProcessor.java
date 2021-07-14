package annotation;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonProcessor {

    public static String toJson(final Object object) {

        Class<?> clazz = object.getClass();
        return Stream.of(clazz.getDeclaredFields())
                .map(field -> {
                    try {
                        field.setAccessible(true); // Set setAccessible = true. De co the truy cap vao private field
                        JsonField jsonFieldName = field.getDeclaredAnnotation(JsonField.class); //
                        String fieldName = Optional.ofNullable(jsonFieldName)
                                .map(JsonField::value)
                                .orElse(field.getName());
                        String prePostFix = field.getType() == String.class || field.getType() == char.class || !field.getType().isPrimitive() ? "\"" : "";

                        return String.format("\"%s\": %s", fieldName, prePostFix + field.get(object) + prePostFix);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("cannot process object to json");
                    }
                })
                .collect(Collectors.joining(",", "{", "}"));
    }
    public static String toJsonWithoutModify(final Object object) {

        Class<?> clazz = object.getClass();
        return Stream.of(clazz.getDeclaredFields())
                .map(field -> {
                    try {
                        JsonField jsonFieldName = field.getDeclaredAnnotation(JsonField.class); //
                        String fieldName = Optional.ofNullable(jsonFieldName)
                                .map(JsonField::value)
                                .orElse(field.getName());
                        String prePostFix = field.getType() == String.class || field.getType() == char.class || !field.getType().isPrimitive() ? "\"" : "";

                        return String.format("\"%s\": %s", fieldName, prePostFix + field.get(object) + prePostFix);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("cannot process object to json");
                    }
                })
                .collect(Collectors.joining(",", "{", "}"));
    }
}
