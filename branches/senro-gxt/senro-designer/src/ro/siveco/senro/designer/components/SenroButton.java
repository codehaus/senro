package ro.siveco.senro.designer.components;

import javax.swing.*;

public class SenroButton extends JButton
{
    private String entity;
    private String task;

    public SenroButton()
    {
        setText("Button");
    }

    public SenroButton(Icon icon)
    {
        super(icon);
        setText("Button");
    }

    public SenroButton(Action a)
    {
        super(a);
        setText("Button");
    }

    public SenroButton(String text)
    {
        super(text);
    }

    public SenroButton(String text, Icon icon)
    {
        super(text, icon);
    }

    public String getEntity()
    {
        return (entity == null ? "" : entity);
    }

    public void setEntity(String entity)
    {
        this.entity = entity;
    }

    public String getTask()
    {
        return (task == null ? "" : task);
    }

    public void setTask(String task)
    {
        this.task = task;
    }

//    public void setText(String text)
//    {
//        super.setText(text);
//    }
//
//    public String getText()
//    {
//        String text = super.getText();
//        return (text == null || text == "") ? SenroButtonBeanFactory.DEFAULT_TEXT : text;
//    }

}
