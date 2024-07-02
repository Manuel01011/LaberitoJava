package app;

import gui.Principal;
import gui.Util;
import gui.menu;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.*;

public class Main{
	public static void main(String [] args){
		try{
			Util.resource=ResourceBundle.getBundle("resources.textos", Locale.getDefault());
		}catch(MissingResourceException mre){
			Locale.setDefault(new Locale("en"));
			Util.resource=ResourceBundle.getBundle("resources.textos", Locale.getDefault());
		}

		menu.menuu = new menu();
		menu.menuu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		menu.menuu.setContentPane(menu.menuu.getPanel1());
		menu.menuu.setSize(900,700);
		menu.menuu.setLocationRelativeTo(null);
		menu.menuu.setVisible(true);

	}
}
