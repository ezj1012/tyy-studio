package com.tyy.studio.fun.markdown;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.DefaultStyledDocument;

public class MDDocument extends DefaultStyledDocument {

    public static int a() {

        try {

            return 2;
        } catch (Exception e) {
            return 3;
        } finally {
            return 1;
        }
    }

    public static void main(String[] args) {
        List<String> a = new ArrayList<String>();
//        a.remove(index)
        System.out.println(a());
    
    }

}
