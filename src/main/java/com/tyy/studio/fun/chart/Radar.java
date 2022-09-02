package com.tyy.studio.fun.chart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Radar {

    Polygon p = new Polygon();

    List<Entry> entry = new ArrayList<>();

    private final Object sync = new Object();

    int x;// 坐标x

    int y; // 坐标y

    int r; // 半径

    public Radar(int x, int y, int r) {
        super();
        moveTo(x, y, r);
    }

    public void add(String name, Number value, Number maxValue, String desc) {
        synchronized (sync) {
            entry.add(new Entry(name, value, maxValue, desc));
            refreshPolygon();
        }
    }

    public Entry getFirst(String name) {
        synchronized (sync) {
            for (Entry entry2 : entry) {
                if (Objects.equals(entry2.name, name)) { return entry2; }
            }
        }
        return null;
    }

    public List<Entry> get(String name) {
        synchronized (sync) {
            return entry.stream().filter(e -> Objects.equals(e.name, name)).collect(Collectors.toList());
        }
    }

    public boolean removeFirst(String name) {
        synchronized (sync) {
            Iterator<Entry> iterator = entry.iterator();
            while (iterator.hasNext()) {
                Entry next = iterator.next();
                if (Objects.equals(next.name, name)) {
                    iterator.remove();
                    refreshPolygon();
                    return true;
                }
            }
            return false;
        }
    }

    public boolean remove(String name) {
        boolean r = false;
        synchronized (sync) {
            Iterator<Entry> iterator = entry.iterator();
            while (iterator.hasNext()) {
                Entry next = iterator.next();
                if (Objects.equals(next.name, name)) {
                    r = true;
                    iterator.remove();
                }
            }
            if (r) {
                refreshPolygon();
            }
            return r;
        }
    }

    public List<Entry> getAll() {
        return new ArrayList<Radar.Entry>(entry);
    }

    public void removeAll() {
        entry.clear();
    }

    private void refreshPolygon() {
        p.reset();
        if (entry.size() > 2) {
            int are = 360 / entry.size();
            p.addPoint(x, y - r);
            for (int i = 1; i < entry.size(); i++) {
                int[] point = getPoint(are * i);
                p.addPoint(point[0], point[1]);
            }
        }
    }

    /**
     * 移动到
     * 
     * @param x
     *            坐标X
     * @param y
     *            坐标Y
     */
    public void moveTo(int x, int y) {
        moveTo(x, y, r);
    }

    /**
     * 
     * @param x
     * @param y
     * @param r
     */
    public void moveTo(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
        synchronized (sync) {
            refreshPolygon();
        }
    }

    public int getSize() {
        return entry.size();
    }

    public Polygon getPolygon() {
        synchronized (sync) {
            return p;
        }
    }
    
    public void paint(Graphics g) {
        
    }
    

    public static class Entry {

        private String name;

        private Number value;

        private Number maxValue;

        private String desc;

        public Entry(String name, Number value, Number maxValue, String desc) {
            super();
            this.name = name;
            this.value = value;
            this.maxValue = maxValue;
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public String getName() {
            return name;
        }

        public Number getValue() {
            return value;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(Number value) {
            this.value = value;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Number getMaxValue() {
            return maxValue;
        }

        public void setMaxValue(Number maxValue) {
            this.maxValue = maxValue;
        }

    }

    private int[] getPoint(int a0) {
        a0 = a0 < 90 ? 360 + a0 - 90 : a0 - 90;
        System.out.println(a0);
        int[] xy = new int[2];
        xy[0] = (int) (x + r * Math.cos(a0 * Math.PI / 180));
        xy[1] = (int) (y + r * Math.sin(a0 * Math.PI / 180));
        return xy;
    }

    public static void main(String[] args) {
        int x0 = 0;
        int y0 = 0;
        int r = 10;
        int a0 = 0;

        int x1 = (int) (x0 + r * Math.cos(a0 * Math.PI / 180));
        int y1 = (int) (y0 + r * Math.sin(a0 * Math.PI / 180));
        System.out.println(x1 + " " + y1);
        JFrame a = new JFrame();
        a.getContentPane().setLayout(null);
        Radar radar = new Radar(300, 300, 100);
        JPanel comp = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.white);
                g.fillRect(0, 0, 1000, 600);
                radar.paint(g);
            }

        };
        comp.setLayout(null);

        JButton ad = new JButton("添加");
        JButton de = new JButton("减少");
        JButton re = new JButton("刷新");

        ad.addActionListener(e -> {
            radar.add("你好", 100, 100, "你好");
            a.repaint();
        });

        de.addActionListener(e -> {
            radar.removeFirst("你好");
            a.repaint();
        });
        re.addActionListener(e -> {
            radar.refreshPolygon();
            a.repaint();
        });

        ad.setBounds(10, 10, 100, 30);
        de.setBounds(10, 50, 100, 30);
        re.setBounds(10, 90, 100, 30);
        comp.add(ad);
        comp.add(de);
        comp.add(re);

        comp.setBounds(0, 0, 600, 600);
        a.getContentPane().add(comp);
        a.setBounds(400, 300, 600, 600);
        a.setVisible(true);
    }

}
