package top.afanee.blog.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import top.afanee.blog.po.enums.DateTimePatternEnum;
import top.afanee.blog.po.enums.ResponseCode;
import top.afanee.blog.po.vo.UeditorResponse;
import top.afanee.blog.utils.Constants;
import top.afanee.blog.utils.DateUtil;


@Controller
public class ImageUploader{
	private static final int MAX_FILE_MAX = 3 * 1024 * 1024;
	@ResponseBody
	@RequestMapping("imageUpload.action")
	public Map<String, Object> imageUpload(HttpSession session, MultipartHttpServletRequest multirequest, 
			HttpServletResponse response){
				String realPath =  Constants.ABSOLUTEPATH + "/upload";
				Map<String, Object> map = new HashMap<String, Object>();
				Iterator<String> itr = multirequest.getFileNames();
				if(itr.hasNext()){
					MultipartFile multipartFile = multirequest.getFile(itr.next());
					long size = multipartFile.getSize();
					if(size > MAX_FILE_MAX){
						map.put("responseCode", ResponseCode.BUSSINESSERROR);
						map.put("msg", "文件不能超过3M");
						return map;
					}
					String fileName = multipartFile.getOriginalFilename();
					String suffix = fileName.substring(fileName.lastIndexOf(".")  + 1);
					if(!"JPG".equalsIgnoreCase(suffix) && !"BMP".equalsIgnoreCase(suffix) &&
							!"gif".equalsIgnoreCase(suffix) && !"PNG".equalsIgnoreCase(suffix)){
						map.put("responseCode", ResponseCode.BUSSINESSERROR);
						map.put("msg", "只能上传图片");
						return map;
					}
					String current = String.valueOf(System.currentTimeMillis());
					fileName = current + "." + suffix;
					String saveDir = DateUtil.format(new Date(), DateTimePatternEnum.YYYYMM.getPattern());
					String savePath = saveDir + "/" + fileName;
 					String fileDir = realPath + "/" + saveDir;
 					File dir = new File(fileDir);
 					if(!dir.exists()){
 						dir.mkdirs();
 					}
					String filePath = fileDir + "/" + fileName;
					File file = new File(filePath);
					try {
						multipartFile.transferTo(file);
						map.put("responseCode", ResponseCode.SUCCESS);
						map.put("savePath", savePath);
						return map;
						} catch (Exception e) {
							map.put("responseCode", ResponseCode.SERVERERROR);
							map.put("msg", "服务器异常,上传失败");
							return map;
					}
				}
				return map;
	}
	
	
	
	@RequestMapping("ueditorImageUpload.action")
	public @ResponseBody UeditorResponse ueditorImageUpload(HttpSession session, MultipartHttpServletRequest multirequest, 
			HttpServletResponse response){
				String realPath = Constants.ABSOLUTEPATH + "/upload";
				UeditorResponse ueditorResponse = new UeditorResponse();
				Iterator<String> itr = multirequest.getFileNames();
				if(itr.hasNext()){
					MultipartFile multipartFile = multirequest.getFile(itr.next());
					long size = multipartFile.getSize();
					if(size > MAX_FILE_MAX){
						ueditorResponse.setState("图片过大");
						return ueditorResponse;
					}
					String fileName = multipartFile.getOriginalFilename();
					String suffix = fileName.substring(fileName.lastIndexOf(".")  + 1);
					if(!"JPG".equalsIgnoreCase(suffix) && !"BMP".equalsIgnoreCase(suffix) &&
							!"gif".equalsIgnoreCase(suffix) && !"PNG".equalsIgnoreCase(suffix)){
						ueditorResponse.setState("只能是图片");
						return ueditorResponse;
					}
					String current = String.valueOf(System.currentTimeMillis());
					fileName = current + "." + suffix;
					String saveDir = DateUtil.format(new Date(), DateTimePatternEnum.YYYYMM.getPattern());
					String savePath = saveDir + "/" + fileName;
 					String fileDir = realPath + "/" + saveDir;
 					File dir = new File(fileDir);
 					if(!dir.exists()){
 						dir.mkdirs();
 					}
					String filePath = fileDir + "/" + fileName;
					File file = new File(filePath);
					try {
						multipartFile.transferTo(file);
						ueditorResponse.setState("SUCCESS");
						ueditorResponse.setUrl(this.getRealPath(multirequest) + "/rece-web/upload/" + savePath);
						return ueditorResponse;
						} catch (Exception e) {
							ueditorResponse.setState("上传参数出错");
							return ueditorResponse;
					}
				}
				return ueditorResponse;
	}
	
	@ResponseBody
	@RequestMapping("imageUpload2Temp.action")
	public Map<String, Object> imageUpload2Temp(HttpSession session, MultipartHttpServletRequest multirequest, 
			HttpServletResponse response){
				String realPath = Constants.ABSOLUTEPATH + "/upload";
				Map<String, Object> map = new HashMap<String, Object>();
				Iterator<String> itr = multirequest.getFileNames();
				if(itr.hasNext()){
					MultipartFile multipartFile = multirequest.getFile(itr.next());
					long size = multipartFile.getSize();
					if(size > MAX_FILE_MAX){
						map.put("responseCode", ResponseCode.BUSSINESSERROR);
						map.put("msg", "文件不能超过3M");
						return map;
					}
					String fileName = multipartFile.getOriginalFilename();
					String suffix = fileName.substring(fileName.lastIndexOf(".")  + 1);
					if(!"JPG".equalsIgnoreCase(suffix) && !"BMP".equalsIgnoreCase(suffix) &&
							!"gif".equalsIgnoreCase(suffix) && !"PNG".equalsIgnoreCase(suffix)){
						map.put("responseCode", ResponseCode.BUSSINESSERROR);
						map.put("msg", "只能上传图片");
						return map;
					}
					String current = String.valueOf(System.currentTimeMillis());
					fileName = current + "." + suffix;
					String saveDir = DateUtil.format(new Date(), DateTimePatternEnum.YYYYMM.getPattern());
					String savePath = saveDir + "/" + fileName;
 					String fileDir = realPath + "/" + saveDir;
 					File dir = new File(fileDir);
 					if(!dir.exists()){
 						dir.mkdirs();
 					}
					String filePath = fileDir + "/" + fileName;
					File file = new File(filePath);
					try {
						multipartFile.transferTo(file);
						map.put("responseCode", ResponseCode.SUCCESS);
						map.put("savePath", savePath);
						return map;
						} catch (Exception e) {
							map.put("responseCode", ResponseCode.SERVERERROR);
							map.put("msg", "服务器异常,上传失败");
							return map;
					}
				}
				return map;
	}
	
	private String getRealPath(HttpServletRequest request){
		String port = request.getServerPort() == 80 ? "": ":" + request.getServerPort();
		String realpath = "http://" + request.getServerName() + port;
		return realpath;
	}
}
