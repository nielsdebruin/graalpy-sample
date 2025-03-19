package com.demo.graalpy;


import org.graalvm.polyglot.Value;
import org.graalvm.python.embedding.utils.GraalPyResources;

import java.util.List;
import java.util.Map;


public class CSVParserExample {

    public static void parse(String csvData) {
        System.out.println("Parsing csv data:\n" + csvData);
        try (var context = GraalPyResources.createContext()) {

            // Execute an import statement of the demo module
            context.eval("python", "import demo");

            // Retrieve the CSVParser class from the Python context
            Value pyCSVParserCls = context
                    .getPolyglotBindings().getMember("CSVParser");

            // Create a new instance of the CSVParser class and cast it to the CSVParser interface
            CSVParser pyCSVParserInst = pyCSVParserCls.newInstance().as(CSVParser.class);

            // Call the parse method of the CSVParser instance
            Dataset dataset = pyCSVParserInst.parse(csvData);

            System.out.println("Dataset column names: " + dataset.get_column_names());
            System.out.println("Dataset rows: " + dataset.get_rows());
            System.out.println("Dataset stats: " + dataset.get_stats());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface CSVParser {
        Dataset parse(String csvData);
    }

    public interface Dataset {
        List<String> get_column_names();

        List<List<Integer>> get_rows();

        Map<String, Object> get_stats();
    }
}
