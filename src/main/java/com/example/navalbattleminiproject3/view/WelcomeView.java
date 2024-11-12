package com.example.navalbattleminiproject3.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class WelcomeView extends Stage {
    private Parent root;

    public WelcomeView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/navalbattleminiproject3/welcome-view.fxml"));
        try{
            root = loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        Scene scene = new Scene(root,1200,670);
        scene.getStylesheets().add(getClass().getResource("/com/example/navalbattleminiproject3/styleWelcome.css").toExternalForm());
        setScene(scene);
        setTitle("Sudoku");
        getIcons().add(new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/favicon.png")));
        setResizable(false);
        initStyle(StageStyle.UNDECORATED);
        show();
    }

    private static class WelcomeViewHolder {
        private static WelcomeView INSTANCE;
    }

    public static WelcomeView getInstance()  {
        WelcomeView.WelcomeViewHolder.INSTANCE =
                WelcomeView.WelcomeViewHolder.INSTANCE != null ?
                        WelcomeView.WelcomeViewHolder.INSTANCE : new WelcomeView();
        return WelcomeView.WelcomeViewHolder.INSTANCE;
    }

    public static void deleteInstance() {
        WelcomeViewHolder.INSTANCE.close();
        WelcomeViewHolder.INSTANCE = null;
    }
}
