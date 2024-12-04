import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

public class ReflectionDeserializer {
    public Object deserialize(String serializedData, String className) throws Exception {
        Class<?> objClass = Class.forName(className);
        Object obj = objClass.getDeclaredConstructor().newInstance();
        String[] lines = serializedData.split("\n");

        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String fieldName = parts[0];
                String fieldValue = parts[1];

                Field field = objClass.getDeclaredField(fieldName);
                field.setAccessible(true);

                // Преобразуем строку в нужный тип
                if (field.getType() == int.class) {
                    field.setInt(obj, Integer.parseInt(fieldValue));
                } else if (field.getType() == double.class) {
                    field.setDouble(obj, Double.parseDouble(fieldValue));
                } else {
                    field.set(obj, fieldValue);
                }
            }
        }

        return obj;
    }
}