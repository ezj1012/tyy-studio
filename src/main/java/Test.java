import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Scrollable;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class Test {

    public static class Task {

        private String content;

        public Task() {

        }

        public Task(String content) {
            super();
            this.content = content;
        }

    }

    public static class TaskRendererFactory {

        public static TaskRenderer getTaskRenderer(Component parent, Task task) {
            return new TaskRenderer(parent, task);
        }

    }

    public static class TaskRenderer extends JPanel {

        private static final long serialVersionUID = 1L;

        private Task task;

        public TaskRenderer(Component parent, Task task) {
            this.task = task;
            this.setBorder(new EmptyBorder(0, 0, 0, 0));
            this.setSize(parent.getWidth(), 50);
        }

        @Override
        public void setSize(int width, int height) {
            super.setSize(width, height);
            this.setPreferredSize(new Dimension(width, height));
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(new Color(100, 100, 100));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.drawString(task.content, 10, 30);
            g.fillRect(0, 0, 10, 10);
            g.fillRect(10, 10, 10, 10);
        }

        public void setTask(Task task) {
            this.task = task;
        }

    }

    public static class TaskGroupPanel extends JScrollPane {

        private static final long serialVersionUID = 1L;

        InnerPanel content = new InnerPanel();

        private int width;

        public TaskGroupPanel() {
            this.setBorder(new EmptyBorder(0, 0, 0, 0));
            this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            this.setBackground(DEFAULT_BACK);
            this.setViewportView(content);
            this.getVerticalScrollBar().setUI(new CommScrollBarUI());
        }

        @Override
        public void setBounds(int x, int y, int width, int height) {
            super.setBounds(x, y, width - 5, height);
            this.width = width - 5;
        }

        @Override
        public void setSize(Dimension d) {
            this.setSize(d.width, d.height);
        }

        @Override
        public void setSize(int width, int height) {
            this.width = width - 5;
            super.setSize(this.width, height);
            this.content.setSize(this.width, height);
        }

        public void addTask(String task) {
            content.addTask(new Task(task));
        }

        public static class InnerPanel extends JPanel implements Scrollable {

            private static final long serialVersionUID = 1L;

            private List<Task> tasks = new ArrayList<>();

            private List<TaskRenderer> renders = new ArrayList<>();

            public InnerPanel() {
                this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
                this.setBorder(new EmptyBorder(0, 0, 0, 0));
            }

            public void setTasks(List<Task> tasks) {
                this.tasks.clear();
                this.tasks.addAll(tasks);
                refreshTask();
            }

            public void addTask(Task task) {
                tasks.add(task);
                refreshTask();
            }

            private synchronized void refreshTask() {
                this.removeAll();
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    TaskRenderer taskRenderer;
                    if (i < renders.size()) {
                        taskRenderer = renders.get(i);
                        taskRenderer = taskRenderer == null ? TaskRendererFactory.getTaskRenderer(this, task) : taskRenderer;
                        renders.set(i, taskRenderer);
                    } else {
                        taskRenderer = TaskRendererFactory.getTaskRenderer(this, task);
                        renders.add(taskRenderer);
                    }
                    taskRenderer.setTask(task);
                    this.add(taskRenderer);
                }
                this.setSize(getPreferredSize());
            }

            public Dimension getPreferredSize() {
                return getPreferredScrollableViewportSize();
            }

            @Override
            public Dimension getPreferredScrollableViewportSize() {
                int h = 0;
                for (TaskRenderer taskRenderer : renders) {
                    h += taskRenderer.getHeight();
                }
                return new Dimension(getWidth(), h);
            }

            @Override
            public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
                return 50;
            }

            @Override
            public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
                return 50;
            }

            @Override
            public boolean getScrollableTracksViewportWidth() {
                return true;
            }

            @Override
            public boolean getScrollableTracksViewportHeight() {
                return false;
            }

        }

    }

    public static class CommScrollBarUI extends BasicScrollBarUI {

        @Override
        protected JButton createDecreaseButton(int orientation) {
            JButton button = super.createDecreaseButton(orientation);
            button.setVisible(false);
            return button;
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            JButton button = super.createIncreaseButton(orientation);
            button.setVisible(false);
            return button;
        }

        public void paint(Graphics g, JComponent c) {
            paintTrack(g, c, getTrackBounds());
            Rectangle thumbBounds = getThumbBounds();
            if (thumbBounds.intersects(g.getClipBounds())) {
                paintThumb(g, c, thumbBounds);
            }
        }

        int thumbH = -1;

        public final static Color hover = new Color(128, 128, 128);

        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) { return; }
            Point mousePosition = c.getMousePosition();
            Rectangle cc = new Rectangle(thumbBounds.x, thumbBounds.y + 2, thumbBounds.width, thumbBounds.height - 2);
            Color cur = mousePosition == null || !cc.contains(mousePosition) ? unhover : hover;
            g.setColor(cur);
            g.fillRect(cc.x + 3, cc.y, 10, cc.height);
        }

        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            g.setColor(unhover);
            g.fillRect(0, 0, 19, c.getHeight());
        }

    }

    public static final int width = 360;

    public static final int height = 400;

    public final static Color DEFAULT_BACK = new Color(100, 100, 100);

    public final static Color unhover = new Color(100, 100, 100, 100);

    public static class Editor extends JLayeredPane {

        private static final long serialVersionUID = 1L;

        private JEditorPane pane = new JEditorPane();

        JScrollPane jsp;

        private JButton okBut = new JButton("确定");

        public Editor() {
            // this.setBackground(DEFAULT_BACK);
            this.setBackground(Color.RED);
            this.setBorder(new EmptyBorder(0, 0, 0, 0));
            this.setLayout(null);

            pane.setBackground(DEFAULT_BACK);
            pane.setForeground(Color.WHITE);
            pane.setCaretColor(Color.WHITE);
            pane.setBorder(new EmptyBorder(15, 10, 15, 10));
            pane.setText("adkjfalkdjfklwejrqlerjqklewrjqlkewjrqlkewrjqlekwjrqlewkrjqlwekjrqlwejrqlewkjrqlwerjqlewrjqwler\n\n\n\n\n\n\n\n\n\n\n\n\n\\n\n\n\n\n\n\n\n\n\n\n");
            jsp = new JScrollPane(pane);
            jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
            jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            jsp.setBounds(0, 0, width, 150);
            jsp.getVerticalScrollBar().setUI(new CommScrollBarUI() {

                protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                    if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) { return; }
                    Rectangle cc = new Rectangle(thumbBounds.x, thumbBounds.y + 2, thumbBounds.width, thumbBounds.height - 2);
                    g.setColor(Color.WHITE);
                    g.fillRect(cc.x, cc.y, 10, cc.height);
                }

                protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                    g.setColor(DEFAULT_BACK);
                    g.fillRect(0, 0, trackBounds.width, c.getHeight());
                }

            });
            //
            // okBut.setBounds(0, 0, 100, 15);
            // okBut.setBorder(new EmptyBorder(0, 0, 0, 0));
            // okBut.setContentAreaFilled(false);
            // this.add(okBut);
            JLabel label = new JLabel("确认");
            label.setBackground(DEFAULT_BACK);
            label.setForeground(Color.WHITE);
            label.setBounds(280, 140, 100, 15);
            label.setFont(new Font("黑体", Font.PLAIN, 15));
            this.add(label, JLayeredPane.PALETTE_LAYER);
            this.add(jsp, JLayeredPane.DEFAULT_LAYER);
        }

    }

    public static void main(String[] args) {
        TaskGroupPanel group = new TaskGroupPanel();
        group.setBounds(0, 0, width, height);

        Editor editor = new Editor();
        editor.setBounds(0, height, width, 150);

        JFrame jf = new JFrame();
        jf.getContentPane().setLayout(null);
        jf.getContentPane().add(group);
        jf.getContentPane().add(editor);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setSize(width, 600);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

}