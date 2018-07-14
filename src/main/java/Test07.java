import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
/**
 * 在指定目录中查找包含关键字的文件(或查找后缀名为XXX的文件)，返回包含指定关键字的文件路径.
 * @author Guozhu Zhu
 * @date 2018/6/3
 * @version 1.0
 *
 */
public class Test07 {
    public static List<File> searchFiles(File folder, final String keyword) {
        List<File> result = new ArrayList<File>();
        if (folder.isFile())
            result.add(folder);

        File[] subFolders = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                if (file.getName().toLowerCase().contains(keyword)) {
                    return true;
                }
                return false;
            }
        });

        /*查找后缀名
         * File[] subFolders = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                if (file.getName().toLowerCase().endsWith(keyword)) {
                    return true;
                }
                return false;
            }
        });*/

        if (subFolders != null) {
            for (File file : subFolders) {
                if (file.isFile()) {
                    // 如果是文件则将文件添加到结果列表中
                    result.add(file);
                } else {
                    // 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                    result.addAll(searchFiles(file, keyword));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<File> files = searchFiles(new File("d:/"), ".ini");
        System.out.println("共找到:" + files.size() + "个文件");
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }
}
