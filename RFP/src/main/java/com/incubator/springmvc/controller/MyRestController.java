//package com.incubator.springmvc.controller;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import org.apache.commons.io.FileUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.incubator.springmvc.model.CustomUserDetails;
//import com.incubator.springmvc.model.UploadedFile;
//import com.incubator.springmvc.service.EmailService;
//import com.incubator.springmvc.utils.CurrentUser;
//import com.incubator.springmvc.utils.DeclareConstant;
//import com.incubator.springmvc.utils.Utilities;
//import com.sun.mail.handlers.multipart_mixed;
//
//@RestController
//public class MyRestController {
//
//	//UploadedFile ufile = ufile = new UploadedFile();
//	@Autowired
//	private EmailService emailService;
//
//	@ResponseBody
//	@RequestMapping(value = "/email", method = RequestMethod.POST)
//	public String email(@RequestParam("FirstName") String firstname, @RequestParam("email") String email,
//			Principal principal) {
//
//		System.out.println("email request");
//		Map<String, Object> model = new HashMap<String, Object>();
//		model.put("from", "tianshiyezi@gmail.com");
//		model.put("subject", "hello springmvc-angularjs-html5");
//		model.put("to", "tianshiyezi@hotmail.com"); // Attention
//		model.put("ccList", new ArrayList<String>());
//		model.put("bccList", new ArrayList<String>());
//		model.put("urludemy", "https://www.udemy.com/spring-framework-4-course-and-core-spring-certification/");
//		model.put("username", "Tuna Tore");
//		// modelAndView.setViewName("project");
//
//		boolean result = emailService.sendEmail("registered.vm", model);
//		if (result == true) {
//			return "success";
//			// return new ModelAndView("success/success", "message", "Email has
//			// been successfully sent");
//			// return new ModelAndView("project", "message", "Email has been
//			// successfully sent");
//			// return modelAndView;
//		}
//		return "failure";
//
//		// return new ModelAndView("error/error", "message", "Error while
//		// sending an email");
//
//	}
//
//	public static String accountTempDirectory() {
//		String rootPath = System.getProperty("catalina.home");
//		String id = String.valueOf(Utilities.getCurrentUserDetails().getId());
//		String subDirectory = File.separator+DeclareConstant.TEMP;
//		String directory = rootPath + File.separator + id + subDirectory;
//		return directory;
//	}
//	
//	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//	public @ResponseBody String handleFileUpload(@RequestParam("fileupload") MultipartFile file) {
//
//		System.out.println("uuid " + Utilities.getCurrentUserDetails().getId());
//
//		if (!file.isEmpty()) {
//
//			try {
//
//				byte[] fileBytes = file.getBytes();
//				String rootPath = System.getProperty("catalina.home");
//				System.out.println("Server rootPath: " + rootPath);
//				System.out.println("File original name: " + file.getOriginalFilename());
//				System.out.println("File content type: " + file.getContentType());
//
//				File newFile = new File(rootPath + File.separator + file.getOriginalFilename());
//				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
//				stream.write(fileBytes);
//				stream.close();
//
//				System.out.println("File is saved under: " + rootPath + File.separator + file.getOriginalFilename());
//				return "File is saved under: " + rootPath + File.separator + file.getOriginalFilename();
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				return "File upload is failed: " + e.getMessage();
//			}
//		} else {
//			return "File upload is failed: File is empty";
//		}
//	}
//
//	@RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
//	public void get(HttpServletResponse response, @PathVariable String value) {
//		try {
//
//			response.setContentType(ufile.getType());
//			response.setContentLength(ufile.getLength());
//			FileCopyUtils.copy(ufile.getBytes(), response.getOutputStream());
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
////	@RequestMapping(value = "/get/image", method = RequestMethod.GET)
////	public @ResponseBody byte[] getImage(HttpServletRequest request,HttpServletResponse response) {
////		byte[] imageBytes= {};
//////		String prefix = Utilities.getCurrentUserDetails().getId()+"_";
////		this.
////		if (files.length != 0) {
////			try {
////
////				String imageName = files[0].getName();
////
////				Path path = Paths.get(accountTempDirectory() + File.separator + 
////						DeclareConstant.IMAGE + File.separator+imageName);
////
////				imageBytes = Files.readAllBytes(path);
////
////				response.setContentType("image/jpeg");
////				response.setContentLength(imageBytes.length);
////
////			} catch (IOException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////		}
////
////
////		return imageBytes;
////	}
//
//
//
////	@RequestMapping(value = "/upload", method = RequestMethod.POST)
////	public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response) {
////		// System.out.println("uuid
////		// "+Utilities.getCurrentUserDetails().getId());
////		// 0. notice, we have used MultipartHttpServletRequest
////
////		// 1. get the files from the request object
////		Iterator<String> itr = request.getFileNames();
////
////		MultipartFile mpf = request.getFile(itr.next());
////		System.out.println(mpf.getOriginalFilename() + " uploaded!");
////
////		try {
////			// just temporary save file info into ufile
////			ufile.setLength(mpf.getBytes().length) ;
////			ufile.setBytes(mpf.getBytes());
////			ufile.setType(mpf.getContentType());
////			ufile.setName(mpf.getOriginalFilename());
////
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////
////		if (!mpf.isEmpty()) {
////
////			try {
////
////				byte[] fileBytes = mpf.getBytes();
////				//			String rootPath = System.getProperty("catalina.home");
////				String id = String.valueOf(Utilities.getCurrentUserDetails().getId());
////				//				System.out.println("Server rootPath: " + rootPath);
////				//				System.out.println("File original name: " + mpf.getOriginalFilename());
////				//				System.out.println("File content type: " + mpf.getContentType());
////				//				String directory = accountDirectory();
////				//				String PREFIX = id+"_";
////				String fileName = id+"_"+mpf.getOriginalFilename();
////				//				System.out.println("directory "+directory);
////				System.out.println("filename "+fileName);
////
////				//			File userDirectory = new File(rootPath + File.separator + id);
////				File userDirectory = new File(accountTempDirectory()+File.separator+DeclareConstant.IMAGE);
////				if (!userDirectory.exists()) {
////					userDirectory.mkdirs();
////				}else{
////					FileUtils.cleanDirectory(userDirectory);
////				}
////
////				//		File newFile = new File(rootPath + File.separator + mpf.getOriginalFilename());
////				File newFile = new File(accountTempDirectory() + File.separator 
////
////						+ DeclareConstant.IMAGE + File.separator + fileName);
////				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
////				stream.write(fileBytes);
////				stream.close();
////
////				//System.out.println("File is saved under: " + rootPath + File.separator + mpf.getOriginalFilename());
////				// return "File is saved under: " + rootPath + File.separator +
////				// mpf.getOriginalFilename();
////
////			} catch (Exception e) {
////				e.printStackTrace();
////				// return "File upload is failed: " + e.getMessage();
////			}
////		}
////
////		// 2. send it back to the client as <img> that calls get method
////		// we are using getTimeInMillis to avoid server cached image
////
////		return "<img src='http://localhost:9090/RFP/get/" + Calendar.getInstance().getTimeInMillis()
////				+ "'  width='304' height='236' />";
////
////	}
//
//	//	@RequestMapping(value="/upload/attachment", method=RequestMethod.POST)
//	//	public ModelAndView uploadAttachment(@RequestParam("fileupload") MultipartFile file){
//	//		System.out.println("fileuploaded?");
//	//		ModelAndView modelAndView = new ModelAndView();
//	//		if (!file.isEmpty()) {
//	//
//	//			try {
//	//
//	//				byte[] fileBytes = file.getBytes();
//	//				String rootPath = System.getProperty("catalina.home");
//	//				System.out.println("Server rootPath: " + rootPath);
//	//				System.out.println("File original name: " + file.getOriginalFilename());
//	//				System.out.println("File content type: " + file.getContentType());
//	//
//	//				File newFile = new File(rootPath + File.separator + file.getOriginalFilename());
//	//				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
//	//				stream.write(fileBytes);
//	//				stream.close();
//	//
//	//				System.out.println("File is saved under: " + rootPath + File.separator + file.getOriginalFilename());
//	//	//			return "File is saved under: " + rootPath + File.separator + file.getOriginalFilename();
//	//
//	//			} catch (Exception e) {
//	//				e.printStackTrace();
//	////				return "File upload is failed: " + e.getMessage();
//	//			}
//	//		} else {
//	////			return "File upload is failed: File is empty";
//	//		}
//	//		modelAndView.setViewName("project");
//	//		return modelAndView;
//	//	}
//
//}
