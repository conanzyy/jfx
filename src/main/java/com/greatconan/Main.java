package com.greatconan;

import com.greatconan.bean.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class Main extends Application {
    public static ConfigurableApplicationContext applicationContext;

    @Resource(name = "user")
    User user;
    @Override
    public void start(Stage primaryStage) throws Exception{
        applicationContext.getBeanFactory();
        user.getId();
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
        //Main.applicationContext = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        //CompletableFuture.supplyAsync(() -> {
        //    ConfigurableApplicationContext ctx = SpringApplication.run(this.getClass());
        //    return ctx;
        //}).thenAccept(this::launchApplicationView);
        Main.applicationContext = SpringApplication.run(Main.class);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
    }
    private void launchApplicationView(ConfigurableApplicationContext ctx) {
        Main.applicationContext = ctx;
    }


}
