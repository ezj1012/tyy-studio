package com.tyy.studio.fun.markdown;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.tyy.studio.utils.SystemUtils;

public class MDParser {

    private List<MDNode> nodes = new ArrayList<MDNode>();

    public void parser(String text) {
        StringTokenizer st = new StringTokenizer(text, "\n");
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            System.out.println(token);
        }
    }

    public List<MDNode> getNodes() {
        return nodes;
    }

    public static void main(String[] args) {
        String text = SystemUtils.readFile(new File("C:\\Users\\Administrator\\Desktop\\拓扑集成DIP_API文档.md"), String.class);
        MDParser p = new MDParser();
//        p.parser(text);
        p.getNodes();
        String a = new String("​```\r\n"
                + "```");
        a= "```\r\n"
        + "1345679\r\n"
        + "​```\r\n"
        + "```\r\n"
        + "\r\n"
        + "";
        
        System.out.println(a.charAt(1) == a.charAt(a.length()-2));
    }

}
