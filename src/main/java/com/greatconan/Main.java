package com.greatconan;

import com.greatconan.service.HttpClientService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class Main extends Application {
    public static ConfigurableApplicationContext applicationContext;

    @Resource(name = "httpClientService")
    HttpClientService httpClientService;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/myfxml.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        //CompletableFuture.supplyAsync(() -> {
        //    ConfigurableApplicationContext ctx = SpringApplication.run(this.getClass());
        //    return ctx;
        //}).thenAccept(this::launchApplicationView);
        Main.applicationContext = SpringApplication.run(Main.class);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
    }
    //private void launchApplicationView(ConfigurableApplicationContext ctx) {
    //    Main.applicationContext = ctx;
    //}
}
