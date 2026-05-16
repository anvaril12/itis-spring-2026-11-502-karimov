package org.example.solve1;

import java.util.List;

public class ValidateException extends Exception {
    private List<String> error;


    public void printError() {
        for(String error1 : error) {
            System.out.println(error1);
        }
    }

    public List<String> getError() {
        return error;
    }

    public ValidateException(List<String> error) {
        super("Validation failed with " + error.size() + " error(s)");
        this.error = error;
    }
}
