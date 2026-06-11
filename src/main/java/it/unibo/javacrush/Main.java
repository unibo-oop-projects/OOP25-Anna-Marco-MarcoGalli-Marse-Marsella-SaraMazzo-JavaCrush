package it.unibo.javacrush;

import javafx.application.Application;

/**
 * Main class of the application.
 */
public final class Main {

    /**
     * This private constructor prevents istantiation.
	 */
	private Main() {

	}

	/**
	 * Main method.
	 * 
	 * @param args ignored.
	 */
	public static void main(final String[] args) {
	    // App è la classe definita nella slide precedente
	    Application.launch(JavaCrushApp.class, args);
	}
}
