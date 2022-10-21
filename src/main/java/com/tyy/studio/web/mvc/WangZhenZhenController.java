package com.tyy.studio.web.mvc;

import java.awt.BorderLayout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyy.studio.web.mvc.bean.WangZhenZhenMsg;

@Controller
@RequestMapping("/")
public class WangZhenZhenController {

    MyFarme myFarme;

    @RequestMapping("/message")
    @ResponseBody
    public synchronized String message(HttpServletRequest request, HttpServletResponse response, @RequestBody(required = false) WangZhenZhenMsg message) {
        if (myFarme == null) {
            myFarme = new MyFarme();
        }
        myFarme.setMsg(message == null ? "" : message.toString());
        myFarme.setVisible(true);
        myFarme.setAlwaysOnTop(true);
        return "1";
    }

    public static class MyFarme extends JFrame {

        private static final long serialVersionUID = 1L;

        private JLabel lable = new JLabel();

        public MyFarme() {
            this.setSize(1000, 400);
            this.setLocationRelativeTo(null);
            this.add(lable, BorderLayout.CENTER);
        }

        void setMsg(String s) {
            lable.setText(s);
        }

    }

}
