import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ReflectionSerializer serializer = new ReflectionSerializer();
        ReflectionDeserializer deserializer = new ReflectionDeserializer();
        String fileName = "data/person.txt"; // Путь к файлу

        try {
            // 1. Загружаем объект из файла
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder serializedData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                serializedData.append(line).append("\n");
            }
            reader.close();

            // Десериализация
            Person person = (Person) deserializer.deserialize(serializedData.toString(), "Person");
            System.out.println("Загруженный объект: " + person.getName() + ", " + person.getAge());

            // 2. Изменяем объект с помощью setter'ов
            person.setName("John Doe");
            person.setAge(30);

            // 3. Сохраняем объект в файл
            String newSerializedData = serializer.serialize(person);
            FileWriter writer = new FileWriter(fileName);
            writer.write(newSerializedData);
            writer.close();

            System.out.println("Объект сохранен: " + newSerializedData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}