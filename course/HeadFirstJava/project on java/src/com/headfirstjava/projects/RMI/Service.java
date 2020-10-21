package com.headfirstjava.projects.RMI;

import javax.swing.*;
import java.io.Serializable;
//не удаленный интерфейс, описывающий единственный метод getGuiPanel()
public interface Service extends Serializable {
    public JPanel getGuiPanel();
}
