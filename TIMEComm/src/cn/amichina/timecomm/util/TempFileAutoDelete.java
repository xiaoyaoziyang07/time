package cn.amichina.timecomm.util;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * 
 * Create by 石磊  on 2015年8月20日 下午4:34:14
 *	临时文件自动删除
 *	
 */
public class TempFileAutoDelete extends FileInputStream{
	private File file; 
	public TempFileAutoDelete(File file) throws FileNotFoundException {
		super(file);
		this.file =file;
	}

	public TempFileAutoDelete(FileDescriptor fdObj) {
		super(fdObj);
	}

	public TempFileAutoDelete(String name) throws FileNotFoundException {
		super(name);
	}
	@Override
	public void close() throws IOException {
		super.close();
		file.delete();
	}
	
}
