import java.io.File;
import java.util.Iterator;

public class AAA {

    private static void inner(File file, int dep, boolean isLast) {
        if(
                file.getName().equals("target") 
                || 
                file.getName().equals(".idea") 
                ) {
            return;
        }
        if (dep == 0) {
            System.out.println(file.getName());
        } else {
            StringBuilder pr = new StringBuilder();

            if (dep > 1) {
                pr.append("│");
                for (int i = 1; i < dep; i++) {
                    pr.append("  ");
                    if (i > 1) {
                        pr.append(" ");
                    }
                }
            }

            if (isLast) {
                pr.append("└─ ");
            } else {
                pr.append("├─ ");
            }
            System.out.println(pr.append(file.getName()));
        }
       
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                File file2 = listFiles[i];
                inner(file2, dep + 1, i == listFiles.length - 1);
            }
        }

    }

    private static void print(File file) {
        inner(file, 0, true);
    }

    public static void main(String[] args) {
        print(new File("D:\\code\\idea\\dip-develop"));
    }

}
