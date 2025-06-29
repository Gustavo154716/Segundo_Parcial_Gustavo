package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ui.components.utils.BaseComponent;
import ui.components.utils.Utils;

public class Label extends JLabel implements BaseComponent {
    private String content;
    private Component parent;

    public Label(String content) {
        super(content);
        this.setForeground(Color.white);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.content = content;
    }

    @Override
    public void setPosition(String horizontal) {
        if (horizontal.equals("center")) {
            this.setLocation(Utils.getCenterX(parent, this), Utils.getCenterY(parent, this));
        }
    }

    @Override
    public void setPosition(String horizontal, String vertical) {
        Point position = Utils.setPosition(parent, this, horizontal, vertical);
        this.setLocation((int) position.getX(), (int) position.getY());
    }

    @Override
    public void setPosition(String horizontal, Integer positionY) {
        Integer positionX = 0;
        if (horizontal.equals("center")) {
            positionX = Utils.getCenterX(parent, this);
        }
        this.setLocation(positionX, positionY);
    }

    @Override
    public void relativeTo(Component parent) {
        this.parent = parent;
    }

    public String getContent() {
        return this.content;
    }
}
