package coffee_management.ui;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public interface MyDocumentListener extends DocumentListener {

	
	@Override
	default void insertUpdate(DocumentEvent e) {
		msg();
	}

	@Override
	default void removeUpdate(DocumentEvent e) {
		msg();
	}

	@Override
	default void changedUpdate(DocumentEvent e) {
		msg();
	}

	public abstract void msg();
}
