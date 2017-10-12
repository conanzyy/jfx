package com.greatconan.main;

import com.greatconan.Main;
import com.greatconan.service.HttpClientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
@SpringBootApplication
public class Controller implements Initializable
{
	@FXML
	private Button myButton;

	@FXML
	private TextField myTextField;

	@Resource(name = "httpClientService")
	HttpClientService httpClientService;

	String content="";
	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Main.applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
	}

	// When user click on myButton
	// this method will be called.
	public void showDateTime(ActionEvent event) {
		System.out.println("Button Clicked!");
		LOGGER.info("Button Clicked!");

		Date now= new Date();

		DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
		String dateTimeString = df.format(now);
		// Show in VIEW
		myTextField.setText(dateTimeString);
		content=httpClientService.doGet("http://www.baidu.com");
		LOGGER.error(content);


	}

	public void changeText(MouseEvent event){
		myTextField.setText(content);

	}
}