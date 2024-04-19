//package dev.cutasevici.button;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//
//public class StartupRunner implements CommandLineRunner {
//    @Autowired
//    private DatabaseService databaseService;
//
//    @Override
//    public void run(String... args) throws Exception {
//        boolean exists = databaseService.checkIfTableExists();
//        System.out.println("Table exists: " + exists);
//    }
//}
