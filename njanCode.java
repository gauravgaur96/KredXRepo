import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ajan {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: Ajan validate | query QUERY_STRING");
            System.exit(1);
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder inputStringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                inputStringBuilder.append(line);
            }
            String inputData = inputStringBuilder.toString().trim();

            if (args[0].equals("validate")) {
                int exitCode = isValidNjan(inputData) ? 0 : 1;
                System.exit(exitCode);
            } else if (args[0].equals("query") && args.length == 2) {
                String queryString = args[1];
                String result = executeQuery(inputData, queryString);
                System.out.println(result);
            } else {
                System.err.println("Invalid command");
                System.exit(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static boolean isValidNjan(String data) {
        try {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(data);

            if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isString()) {
                return true;
            } else if (jsonElement.isJsonArray()) {
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                for (JsonElement element : jsonArray) {
                    if (!isValidNjan(element.toString())) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private static String executeQuery(String data, String query) {
        try {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(data);

            if (query.isEmpty()) {
                return jsonElement.toString();
            }

            String[] indices = query.split("\\.");
            for (String index : indices) {
                try {
                    int arrayIndex = Integer.parseInt(index);
                    if (jsonElement.isJsonArray() && arrayIndex >= 0 && arrayIndex < jsonElement.getAsJsonArray().size()) {
                        jsonElement = jsonElement.getAsJsonArray().get(arrayIndex);
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    throw new Exception("Invalid query");
                }
            }

            return jsonElement.toString();
        } catch (Exception e) {
            System.err.println("Error executing query: " + e.getMessage());
            System.exit(1);
            return null; // unreachable, added to satisfy compiler
        }
    }
}
