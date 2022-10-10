package com.tyy.studio.fun.poi;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.jdbc.Driver;

public class ExcelRead {

    public static void read(String path) {
        read(path, new commRead());
    }

    public static class commRead implements BiConsumer<Integer, XSSFSheet> {

        public static class Data {

            int index;

            String name;

            List<Map<String, String>> datas;

        }

        private List<Data> datas = new ArrayList<ExcelRead.commRead.Data>();

        @Override
        public void accept(Integer idex, XSSFSheet t) {
            String sheetName = t.getSheetName();
            if (t.getLastRowNum() <= 0) { return; }
            final List<String> titles = readTitles(t.getRow(0));
            List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
            for (int i = 1; i < t.getLastRowNum(); i++) {
                Map<String, String> data = new HashMap<String, String>();
                XSSFRow row = t.getRow(i);
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    XSSFCell cell = row.getCell(j);
                    data.put(titles.get(j), readString(cell));
                }
                System.out.println(JSON.toJSONString(data, true));
                datas.add(data);
            }
            try {
                insert(datas);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        private List<String> readTitles(XSSFRow titleRow) {
            // Map<Integer, String> titles = new HashMap<>();
            List<String> titles = new ArrayList<String>();
            short lastCellNum = titleRow.getLastCellNum();
            for (int i = 0; i < lastCellNum; i++) {
                XSSFCell cell = titleRow.getCell(i);
                titles.add(readString(cell));
            }
            return titles;
        }

        private String readString(XSSFCell cell) {
            // String ret = null;
            // CellType cellType = cell.getCellType();
            // switch (cellType) {
            // case _NONE :
            // return null;
            // case NUMERIC :
            // return Double.toString(cell.getNumericCellValue());
            // case STRING :
            // return cell.getStringCellValue();
            // case FORMULA :
            // return
            // case BLANK :
            // return null;
            // case BOOLEAN :
            // return null;
            // case ERROR :
            // return null;
            //
            // }

            return cell.getRichStringCellValue().toString();
        }

    }

    public static void read(String path, BiConsumer<Integer, XSSFSheet> fun) {
        try (XSSFWorkbook wb = new XSSFWorkbook(new File(path));) {
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                XSSFSheet sheet = wb.getSheetAt(i);
                fun.accept(i, sheet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // static LinkedHashMap<String, String> keys = new LinkedHashMap<String, String>();
    static List<String> keys = new ArrayList<>();

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }
        // int i = 1;
        keys.add("应用类型");
        keys.add("所属部门");
        keys.add("业务领域");
        keys.add("应用负责人");
        keys.add("保护等级");
        keys.add("可用性等级");
        keys.add("连续性等级");
        keys.add("测试属性");
        keys.add("环境类型");
        keys.add("应用名称");
        keys.add("应用等级");
        keys.add("应用状态");

        select();
        keys.clear();
        // !=====
        keys.add("部署单元类型");
        keys.add("计算资源类型");
        keys.add("是否被监控");
        keys.add("安全分层");
        keys.add("存储规格");
        keys.add("数据中心");
        keys.add("部署单元名称");
        keys.add("网络区域");
        keys.add("计算规格");
        keys.add("服务类型");
        keys.add("部署实例数量");
        keys.add("安全区域");
        keys.add("所属子系统");
        select();
    }

    public static void select() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, name, class_name,");
        for (int i = 0; i < keys.size(); i++) {
            sql.append("attr").append(i + 1).append(", ");
        }
        sql.delete(sql.length() - 2, sql.length());
        sql.append(" FROM twins WHERE class_name = '\" + className + \"' limit \" + pageStart + \",\" + pageSize");

        System.out.println(sql);
    }

    public static void insert(List<Map<String, String>> datas) throws Exception {

        StringBuilder sql = new StringBuilder();
        // (CLASS_NAME, NAME, ICON, ATTR1, ATTR2, ATTR3, ATTR4, ATTR5, ATTR6, ATTR7, ATTR8, ATTR9, ATTR10, ATTR11, ATTR12, ATTR13, ATTR14)
        // VALUES(NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

        sql.append("   INSERT INTO db_test.demo_video_twin (");
        for (int i = 0; i < keys.size(); i++) {
            sql.append("ATTR").append(i + 1).append(", ");
        }
        sql.append("CLASS_NAME, NAME, ICON) VALUES (");
        for (int i = 0; i < keys.size(); i++) {
            sql.append("?, ");
        }
        sql.append("?, ?, ? )");

        Connection conn = null;
        PreparedStatement pstmt = null;
        if (conn == null) {
            conn = DriverManager.getConnection("jdbc:mysql://172.16.8.98:3306/db_test", "root", "Dix@2021");
            pstmt = conn.prepareStatement(sql.toString());
        }
        System.out.println(sql.toString());

        try {
            for (int i = 0; i < datas.size(); i++) {
                Map<String, String> map = datas.get(i);
                int j = 0;
                for (; j < keys.size(); j++) {
                    pstmt.setString(j + 1, map.get(keys.get(j)));
                }
                // pstmt.setString(j++ + 1, "应用系统");
                // pstmt.setString(j++ + 1, map.get("应用名称"));
                // pstmt.setString(j++ + 1, "https://topo.thingjs.com/topo-static/images/it/AppServer.png");
                pstmt.setString(j++ + 1, "部署单元");
                pstmt.setString(j++ + 1, map.get("部署单元名称"));
                pstmt.setString(j++ + 1, "https://topo.thingjs.com/topo-static/images/it/DeployUnit.png");
                pstmt.addBatch();
            }
            pstmt.executeBatch();

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void aaa() throws Exception {
        String aaa = IOUtils.toString(Files.newInputStream(Paths.get("d://cc.txt")), "UTF-8");
        JSONArray parseArray = JSON.parseObject(aaa).getJSONObject("data").getJSONArray("data");
        for (Object object : parseArray) {
            JSONObject aa = (JSONObject) object;
            System.out.println(aa);
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        if (conn == null) {
            conn = DriverManager.getConnection("jdbc:mysql://172.16.8.98:3306/db_test", "root", "Dix@2021");
            pstmt = conn.prepareStatement("INSERT INTO db_test.demo_video_event\r\n"
            + "(twin_id, severity, severity_name, status, source_alert_key, summary, last_occurrence)\r\n" + //
                    "VALUES(?,?,?,?,?,?,?);");
        }

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat ff = new SimpleDateFormat("yyyyMMddHHmmss");

        try {
            for (int i = 0; i < parseArray.size(); i++) {
                JSONObject aa = (JSONObject) parseArray.get(i);
                int j = 1;

                pstmt.setString(j++, aa.getString("CINAME"));
                pstmt.setString(j++, aa.getString("SEVERITY_CODE"));
                pstmt.setString(j++, aa.getString("SEVERITY"));
                pstmt.setString(j++, aa.getString("STATUS"));
                pstmt.setString(j++, aa.getString("KPINAME"));
                pstmt.setString(j++, aa.getString("SOURCESUMMARY"));

                Date parse = new Date();

                try {
                    if (aa.containsKey("LASTOCCURRENCE")) {
                        parse = sf.parse(aa.getString("LASTOCCURRENCE"));
                    } else {
                        parse = sf.parse(aa.getString("FIRSTOCCURRENCE"));
                    }

                } catch (Exception e) {
                }
                pstmt.setObject(j++, Long.parseLong(ff.format(parse)));

                System.out.println(aa.getString("CINAME") + " _ " + aa.getString("SEVERITY_CODE") + " _ " + aa.getString("STATUS") + " _ " + aa.getString("KPINAME") + " _ " + aa.getString("SOURCESUMMARY") + " _ " + aa.getString("CINAME") + " _ " + Long.parseLong(ff.format(parse))

                );
                pstmt.addBatch();
            }
            pstmt.executeBatch();

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void main(String[] args) throws Exception {
        // String filePath = "C:\\Users\\Administrator\\Desktop\\CI---AAA.xlsx";
        String filePath = "C:\\Users\\Administrator\\Desktop\\CI----BBB.xlsx";
        // read(filePath);

        aaa();
    }

}
