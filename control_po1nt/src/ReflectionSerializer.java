import java.lang.reflect.Field;

public class ReflectionSerializer {
    public String serialize(Object object) throws IllegalAccessException {
        StringBuilder serializedData = new StringBuilder();
        Class<?> objClass = object.getClass();
        Field[] fields = objClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // Делаем поле доступным
            serializedData.append(field.getName()).append(":").append(field.get(object)).append("\n");
        }

        return serializedData.toString();
    }
}