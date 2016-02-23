package cn.amichina.timecomm.report.sertj.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

@Service
public class DownloadService {
	/**
	 * 生成eclx
	 * @param data 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public  File getPath (String data) throws UnsupportedEncodingException {
		List<String[]> rows =new ArrayList<String[]>();
		for (String col : data.split(";")) {
			rows.add(col.split(","));
		}
		File file =null;
		OutputStream outStream =null;
		HSSFWorkbook wb =null;
		Exception runtimeException =null;
		try {
			try {
				file =File.createTempFile("TIMEReport", ".TMP");
				outStream =new FileOutputStream(file);
				wb =new HSSFWorkbook();
				String sheet ="Report";
				wb.createSheet(sheet);
				for (int i = 0; i < rows.size(); i++) {
					Row row = wb.getSheet(sheet).createRow(i);
					String []cols = rows.get(i);
					for (int j = 0; j < cols.length; j++) {
						Cell cell =row.createCell(j);
						cell.setCellValue(new String(cols[j].getBytes("iso-8859-1"),"utf-8"));
						if(i==0){
							Font font = wb.createFont(); 
							CellStyle style = wb.createCellStyle();
							style.setFont(font);
							font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
							font.setFontHeightInPoints((short)12); 
							cell.setCellStyle(style);
						}
					}
				}
				wb.write(outStream);
				return file;
			}catch(Exception e){
				runtimeException = e;
			}
			finally{
				if(wb!=null)wb.close();
				if(outStream!=null)outStream.close();
			}
		} catch (Exception e) {
			if(runtimeException!=null){
				throw new RuntimeException(runtimeException);
			}else{
				throw new RuntimeException(e);
			}
		}
		return file;
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		String data ="日期,文件传输,邮件,游戏,聊天,网管,视频,语音,企业应用,其他,;2015/07/28,0,0,0,29.07,0,0,0,0,87.20,;2015/07/29,0,0,0,29.07,0,0,0,0,87.20,;2015/07/30,0,0,29.07,0,0,0,0,0,0,;2015/07/31,0,0,0,0,0,0,0,0,0,;";
		File f =new DownloadService().getPath(data);
		System.out.println(f);
	}
}
