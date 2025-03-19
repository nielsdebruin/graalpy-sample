package com.demo.graalpy;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.util.Map;

public class App {
    public static void greet() {
        try (var context = Context.newBuilder().allowAllAccess(true).build()) {
            context.eval("python", "print('Hello to you!')");
        }
    }

    public static void coolFunctionExample(String message) {
        // language=Python
        String pythonCode = """
                def cool_function(message):
                    words = message.split()
                    longest_word = max(words, key=len)
                    total_length = len(message)
                    return {'longest_word': longest_word, 'total_length': total_length}
                cool_function
                """;

        try (Context context = Context.newBuilder().allowAllAccess(true).build()) {
            // Evaluate Python code and retrieve the function
            Value coolFunctionValue = context.eval("python", pythonCode);

            // Convert to Java interface
            CoolFunction coolFunction = coolFunctionValue.as(CoolFunction.class);

            // Call the function
            System.out.println("Calling cool_funtion with message: " + message);
            Map<String, Object> result = coolFunction.apply(message);

            System.out.println("Longest word: " + result.get("longest_word"));
            System.out.println("Total length: " + result.get("total_length"));
        }
    }

    @FunctionalInterface
    interface CoolFunction {
        Map<String, Object> apply(String message);
    }

    public static void printUsageGuide() {
        System.out.println("Commands:");
        System.out.println("  greet              - Display a greeting from Python");
        System.out.println("  coolfunction [msg] - Analyze a text message using Python (default message if none provided)");
        System.out.println("  parseCsv           - Demonstrate CSV parsing with Python");
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            printUsageGuide();
            return;
        }

        String cmd = args[0];
        switch (cmd) {
            case "greet" -> greet();
            case "coolfunction" ->
                    coolFunctionExample(args.length >= 2 ? args[1] : "Hello GraalPy and Java interoperability!");
            case "parseCsv" -> {
                final String csvData = """
                        "a","b","c"
                        1,2,3
                        4,5,6
                        7,8,9
                        10,11,12""";
                CSVParserExample.parse(csvData);
            }
            default -> {
                System.out.println("Invalid command: " + cmd);
                printUsageGuide();
            }
        }
    }
}
