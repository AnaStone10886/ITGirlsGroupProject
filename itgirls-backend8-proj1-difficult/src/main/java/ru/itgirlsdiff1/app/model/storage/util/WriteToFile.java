package ru.itgirlsdiff1.app.model.storage.util;

import ru.itgirlsdiff1.app.model.entity.User;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class WriteToFile {
    public static void writeOut(LinkedHashMap<Integer, User> usersHashmap) {
        String filename = "src/resources/Пользователи.txt";
        Iterator<Map.Entry<Integer, User>> iterator = usersHashmap.entrySet().iterator();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            while (iterator.hasNext()) {
                Map.Entry<Integer, User> entry = iterator.next();
                Integer passportNumber = entry.getKey();
                User user = entry.getValue();
                String line = passportNumber + ", " + user;
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
