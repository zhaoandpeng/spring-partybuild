package com.cn.wisdom.utils;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.SystemUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.util.SystemInfo;
import io.netty.util.internal.SystemPropertyUtil;
import net.sf.json.JSONObject;

@Component
@RequestMapping("/api/v1/file/{type}")
public class FileUpload {

	
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/upload")
	@ResponseBody
	public String upload(@PathVariable("type") String type, MultipartFile file, HttpServletRequest request ) throws IOException {
		
		/**
		  *  处理原始文件 将原始文件存储到临时文件目录下
		 */
		String realFileName = file.getOriginalFilename(); //原始文件名
		
		String realPath = request.getServletContext().getRealPath("/");//获取项目相对路径
		
		String joinPath = "temp/" +new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"/";
		
		String writePath = realPath + joinPath;//根据日期创建文件夹组合成文件写入路径
		
		File newFile = new File(writePath+"/"+realFileName);
		
		if(!newFile.getParentFile().exists()){ //判断文件父目录是否存在
            
			newFile.getParentFile().mkdirs();
        }
		
		file.transferTo(newFile); //保存文件
		
		FileUploadChildren data = new FileUpload().new FileUploadChildren();;
		
		data.setSrc("/"+joinPath+realFileName);//InetAddress.getLocalHost().getHostAddress()
		
		FileUpload result = new FileUpload();
		
		result.setCode("0");
		
		result.setMsg("上传成功");
		
		result.setData(data);
		
		return new JSONObject().fromObject(result).toString();
	}
	
	private String code; //0表示成功，其它失败
	
	private String msg;	//提示信息
	
	private FileUploadChildren data; 
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public FileUploadChildren getData() {
		return data;
	}

	public void setData(FileUploadChildren data) {
		this.data = data;
	}

	public class FileUploadChildren{
		
		private String src; 	//图片路径
		
		private String title;	//图片名称

		public String getSrc() {
			return src;
		}

		public void setSrc(String src) {
			this.src = src;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
		
	}
	
//
//E:\devlop\workspace\spring-partybuild\spring-partybuild-web\src\main\webapp\
//E:\devlop\workspace\spring-partybuild\spring-partybuild-web\src\main\webapp\
///api/v1/file/image/upload
//	
//	>>>>>>>>>>>>>>>>>>>>>>>>>>>>OriginalFilename=微信图片_20190118150735.jpg
//			>>>>>>>>>>>>>>>>>>>>>>>>>>>>FileName=file
//			>>>>>>>>>>>>>>>>>>>>>>>>>>>>FileName=image/jpeg
//			>>>>>>>>>>>>>>>>>>>>>>>>>>>>FileName=微信图片_20190118150735.jpg
	
	/*
	 * { "code": 0 //0表示成功，其它失败 ,"msg": "" //提示信息 //一般上传失败后返回 ,"data": { "src":
	 * "图片路径" ,"title": "图片名称" //可选 } }
	 */
	
	public static void main(String[] args) throws UnknownHostException {
		System.out.println(InetAddress.getLocalHost().getHostAddress()+"/");
	}
}
