package Utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.yucong.model.ExcelData;
import com.yucong.model.User;

/**
 * 功能：导入导出
 * 
 */
public class ExcelUtil {

    /**
     * 方法名：exportExcel 功能：导出Excel
     */
    public static void exportExcel(HttpServletResponse response, ExcelData data) {
        System.out.println("导出解析开始，fileName:{}   " + data.getFileName());
        try {
            // 实例化HSSFWorkbook
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 创建一个Excel表单，参数为sheet的名字
            HSSFSheet sheet = workbook.createSheet("aaaaaa");
            // 设置表头
            setTitle(workbook, sheet, data.getHead());
            // 设置单元格并赋值
            setData(sheet, data.getData());
            // 设置浏览器下载
            setBrowser(response, workbook, data.getFileName());
            System.out.println("导出解析成功!");
        } catch (Exception e) {
            System.out.println("导出解析失败!");
            e.printStackTrace();
        }
    }

    /**
     * 方法名：setTitle 功能：设置表头
     */
    private static void setTitle(HSSFWorkbook workbook, HSSFSheet sheet, String[] str) {
        try {
            HSSFRow row = sheet.createRow(0);
            // 设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
            for (int i = 0; i <= str.length; i++) {
                sheet.setColumnWidth(i, 15 * 256);
            }
            // 设置为居中加粗,格式化时间格式
            HSSFCellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
            // 创建表头名称
            HSSFCell cell;
            for (int j = 0; j < str.length; j++) {
                cell = row.createCell(j);
                cell.setCellValue(str[j]);
                cell.setCellStyle(style);
            }
        } catch (Exception e) {
            System.out.println("导出时设置表头失败！");
            e.printStackTrace();
        }
    }

    /**
     * 方法名：setData 功能：表格赋值
     */
    private static void setData(HSSFSheet sheet, List<String[]> data) {
        try {
            int rowNum = 1;
            HSSFRow row = null;
            for (int i = 0; i < data.size(); i++) {
                row = sheet.createRow(rowNum);
                for (int j = 0; j < data.get(i).length; j++) {
                    row.createCell(j).setCellValue(data.get(i)[j]);
                }
                rowNum++;
            }
            System.out.println("表格赋值成功！");
        } catch (Exception e) {
            System.out.println("表格赋值失败！");
            e.printStackTrace();
        }
    }

    /**
     * 方法名：setBrowser 功能：使用浏览器下载
     */
    private static void setBrowser(HttpServletResponse response, HSSFWorkbook workbook, String fileName) {
        try {
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            // 将excel写入到输出流中
            workbook.write(os);
            os.flush();
            os.close();
            System.out.println("设置浏览器下载成功！");
        } catch (Exception e) {
            System.out.println("设置浏览器下载失败！");
            e.printStackTrace();
        }

    }

    /**
     * 导入数据库
     * 
     * @param excelPath
     */
    public static void importExcel(String excelPath) {
        File excel = new File(excelPath);
        if (excel.isFile() && excel.exists()) { // 判断文件是否存在

            System.out.println(excel.getName());
            String[] split = excel.getName().split("\\."); // .是特殊字符，需要转义！！！！！

            // 根据文件后缀（xls/xlsx）进行判断
            Workbook wb = null;
            try {
                if ("xls".equals(split[1])) {
                    FileInputStream fis = new FileInputStream(excel); // 文件流对象
                    wb = new HSSFWorkbook(fis);
                } else if ("xlsx".equals(split[1])) {
                    wb = new XSSFWorkbook(excel);
                } else {
                    System.out.println("文件类型错误!");
                }
            } catch (Exception e) {
            }

            // 开始解析
            Sheet sheet = wb.getSheetAt(0); // 读取sheet 0
            System.out.println(wb.getAllNames());

            int firstRowIndex = sheet.getFirstRowNum() + 1; // 第一行是列名，所以不读
            int lastRowIndex = sheet.getLastRowNum();
            System.out.println("firstRowIndex: " + firstRowIndex);
            System.out.println("lastRowIndex: " + lastRowIndex);
            System.out.println("总共多少行： " + sheet.getPhysicalNumberOfRows());
            System.out.println("一行有多少列： " + sheet.getRow(0).getPhysicalNumberOfCells());

            for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) { // 遍历行
                Row row = sheet.getRow(rIndex);
                if (row != null) {
                    int firstCellIndex = row.getFirstCellNum();
                    int lastCellIndex = row.getLastCellNum();
                    for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) { // 遍历列
                        Cell cell = row.getCell(cIndex);
                        if (cell != null) {
                            System.out.print(cell + "\t");
                        }
                    }
                    System.out.println();
                }
            }
        } else {
            System.out.println("找不到指定的文件");
        }
    }

    /**
     * 导入数据库
     * 
     * @param excelPath
     */
    public static List<User> importExcel1(String excelPath) {
        File excel = new File(excelPath);
        String[] split = excel.getName().split("\\."); // .是特殊字符，需要转义！！！！！

        // 创建excel对象
        Workbook wb = null;
        try {
            // 根据文件后缀（xls/xlsx）进行判断
            if ("xls".equals(split[1])) {
                FileInputStream fis = new FileInputStream(excel); // 文件流对象
                wb = new HSSFWorkbook(fis);
            } else if ("xlsx".equals(split[1])) {
                wb = new XSSFWorkbook(excel);
            } else {
                System.out.println("文件类型错误!");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 在student.xlsx表格里面可能有多个sheet，从0开始
        Sheet sheet = wb.getSheetAt(1);

        // 获取表格第一行
        Row first = sheet.getRow(0);
        String id = first.getCell(0).toString();
        String name = first.getCell(1).toString();
        String age = first.getCell(2).toString();
        System.out.println(id + "\t" + name + "\t" + age);

        List<User> list = new ArrayList<>();

        // 获取sheet中拥有数据的行数
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            // 过滤表头行
            if (i == 0) {
                continue;
            }

            // 获取当前行的数据
            Row row = sheet.getRow(i);
            User user = new User();

            Cell cell1 = row.getCell(0);
            cell1.setCellType(CellType.STRING);
            user.setId(Integer.parseInt(cell1.toString()));

            Cell cell2 = row.getCell(1);
            cell2.setCellType(CellType.STRING);
            user.setName(cell2.toString());

            Cell cell3 = row.getCell(2);
            cell3.setCellType(CellType.STRING);
            user.setAge(Integer.parseInt(cell3.toString()));
            list.add(user);
        }
        return list;
    }

    // 测试导入
    public static void main(String[] args) {
        List<User> list = importExcel1("D:\\student.xlsx");
        list.forEach(System.out::println);
    }

}
