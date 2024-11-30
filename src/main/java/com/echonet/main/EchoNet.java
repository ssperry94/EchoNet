/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.echonet.main;

import javax.swing.SwingUtilities;

import com.echonet.gui.MainFrame;

/**
 *
 * @author sperry94
 */
public class EchoNet {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
