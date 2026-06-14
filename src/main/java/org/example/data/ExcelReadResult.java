package org.example.data;
import org.example.domain.Task;

import java.util.List;

public class ExcelReadResult {

    private final List<Task>records;
    private final List<String> errors;
    public ExcelReadResult(List<Task> records, List<String> errors) {
        this.records = records;
        this.errors = errors;
    }
    public List<Task> getRecords() {
        return records;
    }
    public List<String> getErrors() {
        return errors;
    }

}
