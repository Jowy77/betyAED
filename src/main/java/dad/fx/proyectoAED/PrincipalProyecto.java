package dad.fx.proyectoAED;


import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrincipalProyecto extends Application {
	
	private Tab tab_acces_file;
	private Tab tab_acces_random;
	private Tab tab_acces_xml;
	private Label name_student_label;
	private Label current_path_label;
	private TextField current_path_text;
	private Button make_button;
	private Button delete_button;
	private Button move_button;
	private RadioButton is_directory_check;
	private RadioButton is_file_check;
	private TextField destiny_path_text;
	private Button see_filesDirectories_button;
	private ListView<String> currentDirectoryList_view;
	private Button seeContent_button;
	private Button modifyDirectory_button;
	private TextArea fileContent_area;
	

	@Override
	public void start(Stage primaryStage) throws Exception {
			
		
		//Parte Visual
		current_path_label = new Label("Ruta altual");
		current_path_text = new TextField();
		current_path_text.setPromptText("Introduce la ruta actual");
		make_button = new Button("Crear");
		delete_button = new Button("Eliminar");
		move_button = new Button("Mover");
		is_directory_check = new RadioButton("Es carpeta");
		is_file_check = new RadioButton("Es fichero");
		destiny_path_text = new TextField();
		destiny_path_text.setPromptText("Carpeta o fichero a crear, eliminar o destino a mover");
		see_filesDirectories_button = new Button("Ver ficheros y carpetas");
		currentDirectoryList_view = new ListView<String>();
		seeContent_button = new Button("Ver Contenido Fichero");
		modifyDirectory_button = new Button("Modificar Fichero");
		fileContent_area = new TextArea("Contenido del fichero");
		
		//Funciones de los botones
		make_button.setOnAction(e -> onCrearAction(e));
		delete_button.setOnAction(e -> onBorrarCarpetaAction(e));
		move_button.setOnAction(e -> onMoverAction(e));
		see_filesDirectories_button.setOnAction(e ->onVerFicherosYcarpetas(e));
		seeContent_button.setOnAction(e -> onVerContenidoFichero(e));
		modifyDirectory_button.setOnAction(e -> onModificarFicheero(e));
		
		
		//con esto solo te deja marcar una opcion
		ToggleGroup ficheroCarpetaGroup = new ToggleGroup();
		ficheroCarpetaGroup.getToggles().addAll(is_directory_check,is_file_check);
		ficheroCarpetaGroup.getSelectedToggle();
		
		GridPane my_gridPane = new GridPane();
		
		HBox cajaBotonesCheck = new HBox(5,make_button,delete_button,move_button,is_directory_check,is_file_check);
		
		my_gridPane.setHgap(10);
		my_gridPane.setVgap(10);
		my_gridPane.setPadding(new Insets(15));
		my_gridPane.setGridLinesVisible(false);
		my_gridPane.addRow(0, current_path_label,current_path_text);
		my_gridPane.addRow(1, cajaBotonesCheck);
		my_gridPane.addRow(2, destiny_path_text);
		my_gridPane.addRow(3, see_filesDirectories_button);
		my_gridPane.addRow(4, currentDirectoryList_view);
		my_gridPane.addRow(5, new VBox(5,seeContent_button,modifyDirectory_button),fileContent_area);
		
		current_path_label.setAlignment(Pos.BASELINE_LEFT);
		
		GridPane.setFillWidth(current_path_text, true);
		GridPane.setColumnSpan(current_path_text, 20);
		
		cajaBotonesCheck.setAlignment(Pos.BASELINE_CENTER);
		cajaBotonesCheck.setSpacing(20);
		
		see_filesDirectories_button.setAlignment(Pos.BASELINE_LEFT);
		
		
		
		current_path_label.setAlignment(Pos.BASELINE_LEFT);
		
		ColumnConstraints [] cols = {
				new ColumnConstraints(),
				new ColumnConstraints(),
			};
			
			
			cols[1].setFillWidth(true);
			cols[1].setHgrow(Priority.ALWAYS);
			cols[1].setHalignment(HPos.LEFT);
			
			GridPane.setColumnSpan(cajaBotonesCheck, 2);
			GridPane.setFillWidth(current_path_text, true);
			GridPane.setColumnSpan(current_path_text, 2);
			GridPane.setFillWidth(destiny_path_text, true);
			GridPane.setColumnSpan(destiny_path_text, 2);
			GridPane.setFillWidth(currentDirectoryList_view, true);
			GridPane.setColumnSpan(currentDirectoryList_view, 2);
			
			my_gridPane.getColumnConstraints().setAll(cols);
		
		
		name_student_label = new Label("Joel Couto Lugo");
		BorderPane my_borderPane = new BorderPane();
		my_borderPane.setTop(name_student_label);
		my_borderPane.setCenter(my_gridPane);
		BorderPane.setAlignment(name_student_label, Pos.CENTER);
		BorderPane.setAlignment(my_gridPane, Pos.CENTER);
		
		
		tab_acces_file = new Tab("Acceso a ficheros");
		tab_acces_random = new Tab("Acceso Aleatorio");
	
		tab_acces_xml = new Tab("Acceso XML");
		TabPane root_TabPane = new TabPane(tab_acces_file,tab_acces_random,tab_acces_xml);
		tab_acces_file.setContent(my_borderPane);
		
		Scene scena = new Scene(root_TabPane,640,480);
		
		
		primaryStage.setTitle("JavaFX/Acceso a datos");
		primaryStage.setScene(scena);
		primaryStage.show();


	}
	
	private void onCrearAction(ActionEvent ev) {
		String directorioActual = current_path_text.getText();
		System.out.println(directorioActual);
		String nombreFichero = destiny_path_text.getText();
		System.out.println(nombreFichero);
		File miFichero = new File(directorioActual + "/" + nombreFichero);
		
		try {
		if (is_directory_check.isSelected()) {
			
			miFichero.mkdir();
			
		} else if (is_file_check.isSelected()) {
			
			miFichero.createNewFile();
			
		} else {
			Alert alertaNoSeleccion = new Alert(AlertType.WARNING);
			alertaNoSeleccion.setTitle("Cuidado");
			alertaNoSeleccion.setHeaderText("No has seleccionado lo que quieres crear");
			alertaNoSeleccion.showAndWait();
		}
		
		} catch (IOException e) {
			Alert miAlerta = new Alert(AlertType.ERROR);
			miAlerta.setTitle("ERROR PUTOOO");
			miAlerta.setHeaderText("FALLO EN LA ESA, APESTA!!!");
			miAlerta.setContentText("Error en crear fichero o dirtorio");
			miAlerta.showAndWait();
		}
	}
	
	private void onBorrarCarpetaAction(ActionEvent ev){
		
		String directorioActual = current_path_text.getText();
		System.out.println(directorioActual);
		String nombreFichero = destiny_path_text.getText();
		System.out.println(nombreFichero);
		
		File miFichero = new File(directorioActual + "/" + nombreFichero);
		
		Alert alertBorrado = new Alert(AlertType.CONFIRMATION);
		alertBorrado.setTitle("CONFIRMARCION");
		alertBorrado.setHeaderText("¿Seguro que deseas borrar esta carpeta o directorio?");
		
		Optional<ButtonType> result = alertBorrado.showAndWait();
		
		if(result.get() == ButtonType.OK) {
			
			if(!miFichero.isDirectory()) {
				miFichero.delete();
			}else {
				borrarCarpeta(miFichero);
				miFichero.delete();
			}
			
		}
			
	}
	
	private void borrarCarpeta(File carpeta) {
		
		File[] misFicheros = carpeta.listFiles();
		
		for(int i = 0;i<misFicheros.length;i++) {
			
			if(misFicheros[i].isDirectory()) {
				borrarCarpeta(misFicheros[i]);
			}
			
			misFicheros[i].delete();
			
		}
		
	}
	
	private void onMoverAction(ActionEvent ev) {
		
		File miFichero = new File(current_path_label.getText());
		
		File nuevoFichero = new File(miFichero,destiny_path_text.getText());
		try {
			
			if(nuevoFichero.isDirectory()) {
				borrarCarpeta(miFichero);
				nuevoFichero.mkdir();
			}else {
				miFichero.delete();
				nuevoFichero.createNewFile();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void onModificarFicheero(ActionEvent ev) {
		
	}

	private void onVerContenidoFichero(ActionEvent ev) {
		
	}

	private void onVerFicherosYcarpetas(ActionEvent ev) {
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
