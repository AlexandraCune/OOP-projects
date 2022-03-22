package loadinput;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class LoadInput {
    private final File inputsFile;

    public LoadInput(final String  inputsFile) {
        this.inputsFile = new File(inputsFile);
    }

    /**
     * Folosit la citiea din fisier a datelor de intrare.
     */
    public Input readInput() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
      //  try {
            return objectMapper.readValue(inputsFile, Input.class);
//        } catch (IOException e) {
//            System.out.println("Could not find input file.");
//            System.exit(-1);
//        }
//        return null;

    }

}
