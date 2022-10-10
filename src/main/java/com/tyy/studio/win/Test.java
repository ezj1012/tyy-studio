//package com.tyy.studio.win;
//
//import java.awt.BorderLayout;
//import java.awt.Component;
//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
//import java.math.BigInteger;
//
//import javax.swing.JFrame;
//import javax.swing.WindowConstants;
//
//import com.teamdev.jxbrowser.chromium.Browser;
//import com.teamdev.jxbrowser.chromium.be;
//import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
//import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
//import com.teamdev.jxbrowser.chromium.swing.BrowserView;
//
//public class Test {
//
//    static {
//        try {
//            Field e = be.class.getDeclaredField("e");
//            e.setAccessible(true);
//            Field f = be.class.getDeclaredField("f");
//            f.setAccessible(true);
//            Field modifersField = Field.class.getDeclaredField("modifiers");
//            modifersField.setAccessible(true);
//            modifersField.setInt(e, e.getModifiers() & ~Modifier.FINAL);
//            modifersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
//            e.set(null, new BigInteger("1"));
//            f.set(null, new BigInteger("1"));
//            modifersField.setAccessible(false);
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        Browser browser = new Browser();
//        BrowserView view = new BrowserView(browser);
//
//        JFrame frame = new JFrame();
//        System.out.println(frame.getContentPane().getLayout());
//        for (Component c : frame.getContentPane().getComponents()) {
//            System.out.println(c);
//        }
//        System.out.println("=================================");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.add(view, BorderLayout.CENTER);
//        for (Component c : frame.getContentPane().getComponents()) {
//            System.out.println(c);
//        }
//
//        frame.setSize(700, 500);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//
//        browser.addLoadListener(new LoadAdapter() {
//
//            @Override
//            public void onFinishLoadingFrame(FinishLoadingEvent event) {
//                if (event.isMainFrame()) {
//                    // HTML content has been loaded completely.
//                }
//            }
//
//        });
//        browser.addConsoleListener(arg0 -> {
//            System.out.println(arg0.getMessage());
//        });
//        browser.loadURL("http://localhost/coder/index.html#/");
//        // browser.loadHTML("<html><body><h1>Load HTML Sample</h1></body></html>");
//    }
//
//}
