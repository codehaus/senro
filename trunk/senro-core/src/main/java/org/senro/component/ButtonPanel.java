package org.senro.component;

import wicket.markup.html.panel.Panel;
import wicket.markup.html.form.Button;
import wicket.model.Model;
import org.senro.metadata.exception.NoMetadataFoundException;

/**
 * @author Claudiu Dumitrescu
 */
public abstract class ButtonPanel extends Panel {

    public ButtonPanel(String id) {
        super("buttonPanel");
        Button button = new Button("button", new Model(id)) {
            protected void onSubmit() {
                try {
                    onClick();
                } catch (NoMetadataFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        add(button);
    }

    public abstract void onClick() throws NoMetadataFoundException;

}
