package org.craft.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class InputsUtil {
    private BufferedReader reader;

    public static Optional<BufferedReader> getFileInput(String FILE_NAME){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            return Optional.of(reader);
        } catch (IOException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
