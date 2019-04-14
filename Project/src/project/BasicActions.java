/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.scene.control.TextArea;

/**
 *
 * @author s297163
 */
public class BasicActions {
    
    public BasicActions()
    {
        //TO DO
    }
    
    public void Clear(TextArea matrixTextArea, TextArea vectorTextArea, TextArea resultTextArea)
    {
        matrixTextArea.clear();
        vectorTextArea.clear();
        resultTextArea.clear();
    }
    
    public void Load()
    {
        System.out.println("Load");
    }
    
    public void Save()
    {
        System.out.println("Save");
    }
}
