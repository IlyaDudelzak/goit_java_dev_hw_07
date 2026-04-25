package org.example;

import org.example.exception.DatabaseQueryException;
import org.example.service.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) throws DatabaseQueryException {
        DatabaseInitService.main();
        DatabasePopulateService.main();
        DatabaseQueryService.main();
    }
}
