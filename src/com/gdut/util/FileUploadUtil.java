package com.gdut.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class FileUploadUtil {
	//文件路径
	private String filePath=null;
	public void setFilePath(String filePath){
		this.filePath=filePath;
	}
	public String getFilePath(){
		return filePath;
	}
	//获得文件扩展名
	private String getExt(String fileName){
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
	//生成新的文件名
	private String createFileName(String fileName){
		return UUID.randomUUID().toString()+"."+getExt(fileName);
	}
	public String uploadFile(File file,String fileName){
		String newName=createFileName(fileName);
		InputStream input=null;
		OutputStream output=null;
		try{
			input=new FileInputStream(file);
			output=new FileOutputStream(filePath="/"+newName);
			byte[] b=new byte[1024];
			int length=0;
			while((length=input.read(b))!=-1){
				output.write(b,0,length);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
				try{
					input.close();
				}catch(Exception e){
					throw new RuntimeException(e);
				}finally{
					try{
						output.close();
					}catch(IOException e){
						throw new RuntimeException(e);
					}
				}
		}
		return newName;
	}
}
