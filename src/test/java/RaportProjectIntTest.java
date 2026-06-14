import org.example.data.FileScanner;
import org.example.domain.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class RaportProjectIntTest {

    private static final String TEST_FILE = "resources/reporter-dane/2012/01/Bledna_Janina.xlsx";

    @Test
    public void fileLoadsCorrectly() {
        List<Task> tasks = new FileScanner().readExcelFile(TEST_FILE);
        assertFalse(tasks.isEmpty());
    }


}
