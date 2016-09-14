package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable{
    public static ObservableList<Contact> contacts = FXCollections.observableArrayList();
    public static List<Contact> loadedContacts;

    @FXML
    ListView list;

    @FXML
    TextField name;

    @FXML
    TextField phone;

    @FXML
    TextField email;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        list.setItems(contacts);
    }

    public void addContact() throws IOException{
        if (name.getText().trim().equalsIgnoreCase("") || phone.getText().trim().equalsIgnoreCase("") || email.getText().trim().equalsIgnoreCase("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty Field");
            alert.setContentText("No fields can be left blank.");

            alert.showAndWait();
        } else {
            contacts.add(new Contact(name.getText(), phone.getText(), email.getText()));
            name.setText("");
            phone.setText("");
            email.setText("");
            writeJson();
        }
    }

    public void removeContact() {
        Contact contact = (Contact) list.getSelectionModel().getSelectedItem();
        contacts.remove(contact);
    }

    public void writeJson() throws IOException {
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.serialize(contacts);
        FileWriter fw = new FileWriter("contacts.json");
        fw.write(json);
        fw.close();
    }

}
