package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {
	
	@FXML
	private Button ExitBT;
	
	
	@FXML
	private Button welcomeBT;
	
	@FXML
	private void Welcon(ActionEvent event) throws IOException {
        Parent screen2 = FXMLLoader.load(getClass().getResource("Project.fxml"));
        Scene screen2_scene = new Scene(screen2);
        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();     
        stage2.setScene(screen2_scene);
        stage2.show();  
	}
	
	@FXML
	public void Exit(ActionEvent event) {
		Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
		stage.close();
	}
	
}
