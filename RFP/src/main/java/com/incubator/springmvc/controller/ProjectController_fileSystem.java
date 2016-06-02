//package com.incubator.springmvc.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.security.Principal;
//import java.util.List;
//import java.util.UUID;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//
//import org.apache.commons.io.FileUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.bind.support.SessionStatus;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.incubator.springmvc.model.Contact;
//import com.incubator.springmvc.model.Project;
//import com.incubator.springmvc.model.UploadedFile;
//import com.incubator.springmvc.service.AccountService;
//import com.incubator.springmvc.service.ContactService;
//import com.incubator.springmvc.service.ProjectService;
//import com.incubator.springmvc.service.UploadedFileService;
//import com.incubator.springmvc.utils.DeclareConstant;
//import com.incubator.springmvc.utils.Utilities;
//import com.incubator.springmvc.validation.validators.TitleValidator;
//
//@Controller
// @SessionAttributes("project")
//public class ProjectController_fileSystem {
//
//	private ProjectService projectService;
//	private TitleValidator titleValidator;
//	private ContactService contactService;
//	private UploadedFileService uploadedFileService;
//	private AccountService accountService;
//
//	@Autowired
//	public void setTitleValidator(TitleValidator titleValidator) {
//		this.titleValidator = titleValidator;
//	}
//
//	@Autowired
//	public void setUploadedFileService(UploadedFileService uploadedFileService) {
//		this.uploadedFileService = uploadedFileService;
//	}
//
//	@Autowired
//	public void setProjectService(ProjectService projectService) {
//		this.projectService = projectService;
//	}
//
//	@Autowired
//	public void setContactService(ContactService contactService) {
//		this.contactService = contactService;
//	}
//
//	@Autowired
//	public void setAccountService(AccountService accountService) {
//		this.accountService = accountService;
//	}
//
//	@ModelAttribute("project")
//	public Project createProjectBean() {
//		return new Project();
//	}
//
//	@RequestMapping(value = { "/project" }, method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView createProject(Principal principal, @ModelAttribute("project") Project project,Model model) {
//		ModelAndView modelAndView = new ModelAndView();
//		List<UploadedFile> filesList = (List<UploadedFile>) model.asMap().get("filesList");
////		if (model.asMap().containsKey("projectBindingResult"))
////	    {
////	        model.addAttribute("org.springframework.validation.BindingResult.project",
////	                model.asMap().get("projectBindingResult"));
////	    }
////		Object result = model.asMap().get("org.springframework.validation.BindingResult.project");
////		modelAndView.addObject("result", result);
//		String imagePath = (String) model.asMap().get("imagePath");
//		List<UploadedFile> imageFileList = this.uploadedFileService
//				.listImage(Utilities.accountTempImageDirectory());
//		if (null == filesList) {
//			filesList = this.uploadedFileService
//					.listFiles(Utilities.accountTempAttachDirectory());
//			modelAndView.addObject("filesList", filesList);
//		}
//		if (imageFileList.size() > 0) {
//			imagePath = "get/image";
////			imagePath = "https://s3.amazonaws.com/crowdfundingbucket/test/temp/img_1.jpg";
//			modelAndView.addObject("imagePath", imagePath);
//			modelAndView.addObject("imageFileList", imageFileList);
//			System.out.println("imagepath " + imagePath);
//
//		}
//		modelAndView.setViewName("project");
//		List<Contact> contacts = this.contactService.listContacts(principal.getName());
//
//		if (principal.getName().equals("grace@abc.com")) {
//			modelAndView.addObject("permissions", "ROLE_PCREATOR");
//		}
//
//		String name = principal.getName();
//		// System.out.println("project creator "+name);
//		modelAndView.addObject("username", name);
//		modelAndView.addObject("contactList", contacts);
////		System.out.println("utility " + Utilities.getCurrentUserDetails().getId());
//		UUID user_id = Utilities.getCurrentUserDetails().getId();
//		modelAndView.addObject("user_id", user_id.toString());
//
//		return modelAndView;
//
//	}
//
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		// binder.setValidator(userValidator);
//		binder.addValidators(titleValidator);
//		binder.registerCustomEditor(UUID[].class, new UUIDEditor());
//	}
//
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PCREATOR')")
//	@RequestMapping(value = "/project/add", method = {RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView addProject(@ModelAttribute("project") @Valid Project project, BindingResult result, Model model,
//			Principal principal, RedirectAttributes redirectAttributes, SessionStatus status) {
//
//		ModelAndView modelAndView = new ModelAndView();
//		if (result.hasErrors()) {
//			List<UploadedFile> imageFileList = this.uploadedFileService
//					.listImage(Utilities.accountTempImageDirectory());
//			List<UploadedFile> filesList = this.uploadedFileService
//					.listFiles(Utilities.accountTempAttachDirectory());
//			modelAndView.addObject("filesList", filesList);
//
//			if (imageFileList.size() > 0) {
//				modelAndView.addObject("imagePath", "get/image");
//				modelAndView.addObject("imageFileList", imageFileList);
////				System.out.println("imagepath " + imagePath);
//
//			}
//			modelAndView.setViewName("/project");
////			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.project", result);
////	        redirectAttributes.addFlashAttribute("project", project);
//
//			return modelAndView;
//		}
////		status.setComplete();
//
//		System.out.println("project summary " + project);
//		UUID projectId = this.projectService.addProject(project, principal);
//		this.projectService.setAttachmentPath(projectId);
//		 modelAndView.setViewName("redirect:/project/projects");
//		return modelAndView;
//	}
//
//	
//	@RequestMapping(value = "/project/projects", method = RequestMethod.GET)
//	public ModelAndView listProjects(Principal principal,HttpServletRequest httpServletRequest) {
//		ModelAndView model = new ModelAndView();
//		
////		String inPreviewLink = httpServletRequest.getContextPath()+"/project/";
//		List<Project> projects = projectService.listProjects(principal.getName());
////		this.projectService.editPreviewLink(projects,inPreviewLink);
////		System.out.println("rootpath"+inPreviewLink);
////		List<String> titles = new ArrayList<String>();
//
////		for (int i = 0; i < projects.size(); i++) {
////			titles.add(projects.get(i).getTitle());
////		}
//		model.addObject("myprojects", projects);
//		System.out.println("project "+projects);
//		// System.out.println("projects exist:" + projects);
//		model.setViewName("projects");
//		return model;
//
//	}
//
//	@RequestMapping(value = "/project/upload/attachment", method = { RequestMethod.POST })
////	public String uploadAttachment(HttpServletRequest request ,@RequestParam(value="fileupload",required=false) MultipartFile file, Model model,
////			RedirectAttributes redirectAttributes) {
//		public String uploadAttachment(HttpServletRequest request,Model model,
//				RedirectAttributes redirectAttributes) {
//		if (!(request instanceof MultipartHttpServletRequest)) {
//			System.out.println("multipartrequest" + (request instanceof MultipartHttpServletRequest));
//			return "redirect:/project";
//		}else{
//			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//	        MultipartFile file = multipartRequest.getFile("fileupload");
//			UploadedFile uploadedFile = new UploadedFile();
//			String path = Utilities.accountTempAttachDirectory();
//			if (!file.isEmpty()) {
//
//				try {
//
//					uploadedFile.setLength(file.getBytes().length);
//					uploadedFile.setName(file.getOriginalFilename());
//					uploadedFile.setType(file.getContentType());
//					uploadedFile.setBytes(file.getBytes());
//					uploadedFile.setPath(path);
//					uploadedFile.setRoleInProject(DeclareConstant.ATTACHMENTS);
//					this.uploadedFileService.addFile(uploadedFile);
//
//					File fileDirectory = new File(path);
//					FileUtils.forceMkdir(fileDirectory);
//					File writeFile = new File(path + File.separator + uploadedFile.getName());
//					FileUtils.writeByteArrayToFile(writeFile, uploadedFile.getBytes());
//
//				} catch (Exception e) {
//					e.printStackTrace();
//					// return "File upload is failed: " + e.getMessage();
//				}
//			}
//
//			List<UploadedFile> filesList = this.uploadedFileService.listFiles(path);
//			redirectAttributes.addFlashAttribute("filesList", filesList);
//
//			System.out.println("filelist" + filesList.size());
//
//			// modelAndView.addObject("hello", "hello");
//
//			// modelAndView.setViewName("redirect:/project");
//			// return "redirect:/project";
//			return "redirect:/project";
//		}
////		System.out.println("multipartrequest" + (request instanceof MultipartHttpServletRequest));
//		// System.out.println("fileuploaded?");
//		// ModelAndView modelAndView = new ModelAndView();
//		
//	}
//
//	@RequestMapping(value = "/project/upload/attachment", method = RequestMethod.GET)
//	public String redirect() {
//
//		return "redirect:/project";
//		//return "project";
//	}
//	
//	@RequestMapping(value = "/project/upload/image", method = RequestMethod.GET)
//	public String redirectImage() {
//
//		return "redirect:/project";
//		//return "project";
//	}
//
//	@RequestMapping(value = { "/download/attachment/{id}" }, method = RequestMethod.GET)
//	public void downloadDocument(@PathVariable UUID id, HttpServletResponse response) throws IOException {
//		UploadedFile document = uploadedFileService.findById(id);
//		File downloadFile = Utilities.listFiles(document.getPath())[0];
//		InputStream inputStream = FileUtils.openInputStream(downloadFile);
//		response.setContentType(document.getType());
//		response.setContentLength(document.getLength());
//		response.setHeader("Content-Disposition", "attachment; filename=\"" + document.getName() + "\"");
//
//		FileCopyUtils.copy(inputStream, response.getOutputStream());
//		response.getOutputStream().flush();
//		response.getOutputStream().close();
//
//		// return "project";
//	}
//
//	@RequestMapping(value = { "/delete/attachment/{id}" }, method = RequestMethod.GET)
//	public String deleteDocument(@PathVariable UUID id) {
//
//		try {
//			UploadedFile uploadedFile = this.uploadedFileService.findById(id);
//			File deleteFile = new File(uploadedFile.getPath() + File.separator + uploadedFile.getName());
//			FileUtils.forceDelete(deleteFile);
//			this.uploadedFileService.deleteById(id);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return "redirect:/project";
//	}
//
//	@RequestMapping(value = "/project/upload/image", method = RequestMethod.POST)
////	public ModelAndView uploadImage(HttpServletRequest request, @RequestParam(value="imageUpload",required=false) MultipartFile file, Model model,
////			RedirectAttributes redirectAttributes) {
//		public ModelAndView uploadImage(HttpServletRequest request,@ModelAttribute("project") Project project,Model model,
//				RedirectAttributes redirectAttributes) {
//		System.out.println("project info "+project.getTitle());
//		ModelAndView modelAndView = new ModelAndView();
//		if ((request instanceof MultipartHttpServletRequest)) {
//			UploadedFile uploadedFile = new UploadedFile();
//			String path = Utilities.accountTempImageDirectory();
//			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//
////			DiskFileItemFactory factory = new DiskFileItemFactory();
////			ServletFileUpload upload = new ServletFileUpload(factory);
////			try {
////				List items = upload.parseRequest(multipartRequest);
////				System.out.println("items "+items.size());
////			} catch (FileUploadException e1) {
////				// TODO Auto-generated catch block
////				e1.printStackTrace();
////			}
//			
//	        MultipartFile file = multipartRequest.getFile("imageUpload");
//			if (!file.isEmpty()) {
//
//				try {
//
//					uploadedFile.setLength(file.getBytes().length);
//					uploadedFile.setName(file.getOriginalFilename());
//					uploadedFile.setType(file.getContentType());
//					uploadedFile.setBytes(file.getBytes());
//					uploadedFile.setPath(path);
//					uploadedFile.setRoleInProject(DeclareConstant.IMAGE);
//					this.uploadedFileService.addFile(uploadedFile);
//
//					File fileDirectory = new File(path);
//					FileUtils.forceMkdir(fileDirectory);
//					if (Utilities.listFiles(path).length > 0) {
//						this.uploadedFileService.deleteByNameAndPath(Utilities.listFiles(path)[0].getName(), path);
//					}
//					FileUtils.cleanDirectory(fileDirectory);
//					File writeFile = new File(path + File.separator + uploadedFile.getName());
//					FileUtils.writeByteArrayToFile(writeFile, uploadedFile.getBytes());
//
//				} catch (Exception e) {
//					e.printStackTrace();
//					// return "File upload is failed: " + e.getMessage();
//				}
//			}
//			
//			// modelAndView.addObject("imagePath", "<img
//			// src='${pageContext.request.contextPath}/get/image' width='304'
//			// height='206' />");
//			redirectAttributes.addFlashAttribute("imagePath", "get/image");
//		//	modelAndView.addObject("imagePath", "get/image");
//		}
//		System.out.println("imagenotmultiple");
//		modelAndView.setViewName("redirect:/project");
//		//modelAndView.setViewName("project");
//		// 2. send it back to the client as <img> that calls get method
//		// we are using getTimeInMillis to avoid server cached image
//
//		/// * return "<img src='http://localhost:9090/RFP/get/" +
//		/// Calendar.getInstance().getTimeInMillis()
//		// + "' width='304' height='236' />";
//		return modelAndView;
//	}
//
//	@RequestMapping(value = "/get/image", method = RequestMethod.GET)
//	public @ResponseBody byte[] getImage(HttpServletRequest request, HttpServletResponse response) {
//		byte[] imageBytes = {};
//		String path = Utilities.accountTempDirectory() + File.separator + DeclareConstant.IMAGE;
//		List<UploadedFile> files = this.uploadedFileService.listFiles(path);
//		if (files.size() > 0) {
//			String imageName = files.get(0).getName();
//
//			Path imagePath = Paths.get(path + File.separator + imageName);
//
//			try {
//				imageBytes = Files.readAllBytes(imagePath);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//			response.setContentType("image/jpeg");
//			response.setContentLength(imageBytes.length);
//		}
//
//		return imageBytes;
//	}
//	
//	@RequestMapping(value = "/get/image/{id}", method = RequestMethod.GET)
//	public @ResponseBody byte[] getImagePreview(@PathVariable ("id") UUID projectId, HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("get?");
//		byte[] imageBytes = {};
//		String path = Utilities.getAccountImageDirectory(this.accountService.getCreatorIdFromProjectId(projectId), projectId);
//		List<UploadedFile> files = this.uploadedFileService.listFiles(path);
//		System.out.println("size "+files.size());
//		if (files.size() > 0) {
//			String imageName = files.get(0).getName();
//
//			Path imagePath = Paths.get(path + File.separator + imageName);
//
//			try {
//				imageBytes = Files.readAllBytes(imagePath);
//	//			System.out.println("anyimages "+imageBytes.length);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//			response.setContentType("image/jpeg");
//			response.setContentLength(imageBytes.length);
//		}
//
//		return imageBytes;
//	}
//	
//	public void getPreview(UUID projectId,ModelAndView modelAndView) {
//		Project project = this.projectService.getProjectById(projectId);
//		modelAndView.addObject("project", project);
//		UUID creatorId = this.accountService.getCreatorIdFromProjectId(projectId);
//		List<UploadedFile> imageFileList = this.uploadedFileService
//				.listImage(Utilities.getAccountImageDirectory(creatorId, projectId));
//
//		String path = "get/image/"+projectId;
//		if (imageFileList.size() > 0) {
//			modelAndView.addObject("imagePath", path);
//			modelAndView.addObject("imageFileList", imageFileList);
//
//		}
//		
//		List<UploadedFile> filesList = this.uploadedFileService
//					.listFiles(Utilities.getAccountAttachDirectory(creatorId, projectId));
//			modelAndView.addObject("filesList", filesList);
//
//	}
//	
//	@RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
//	public ModelAndView getProjectById(@PathVariable("id") UUID projectId) {
//		ModelAndView modelAndView = new ModelAndView();
//		getPreview(projectId, modelAndView);
////		Project project = this.projectService.getProjectById(projectId);
////		modelAndView.addObject("project", project);
//////		String displayOK = "";
//////		String displayAcceptance ="";
//////		String projectRole = this.accountService.getProjectRole(principal, projectId);
//////		if (Utilities.hasRole("ROLE_ADMIN") || "ROLE_PCREATOR".equals(projectRole) || "ROLE_POWNER".equals(projectRole)) {
//////			displayOK = DeclareConstant.DISPLAY;
//////			modelAndView.addObject("displayOK", displayOK);
//////		}else if ("ROLE_USER".equals(projectRole)) {
//////			displayAcceptance = DeclareConstant.DISPLAY;
//////			modelAndView.addObject("displayAcceptance", displayAcceptance);
//////		}
////	//	System.out.println("project "+projectId+project);
////		
////		List<UploadedFile> imageFileList = this.uploadedFileService
////				.listImage(Utilities.changeAccountImageDirectory(projectId));
////
////		String path = "get/image/"+projectId;
////		if (imageFileList.size() > 0) {
////			modelAndView.addObject("imagePath", path);
////			modelAndView.addObject("imageFileList", imageFileList);
////
////		}
////		
////		List<UploadedFile> filesList = this.uploadedFileService
////					.listFiles(Utilities.changeAccountAttachDirectory(projectId));
////			modelAndView.addObject("filesList", filesList);
//
//	//	this.projectService.setAttachmentPath(projectId);
//		modelAndView.setViewName("project/preview");
//		return modelAndView;
//	}
//	
//	@RequestMapping(value = "/{summary}/{id}", method = RequestMethod.GET)
//	public ModelAndView getPreviewWithSummaryForUser(@PathVariable("id") UUID projectId, 
//			@PathVariable("summary") String summary) {
//	//	System.out.println("user projectid"+projectId);
//		ModelAndView modelAndView = new ModelAndView();
//		getPreview(projectId, modelAndView);
//		modelAndView.setViewName("user/preview");
//		modelAndView.addObject("summary", "summary");
//		return modelAndView;
//	}
//	
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ModelAndView getPreviewForUser(@PathVariable("id") UUID projectId) {
////		System.out.println("user projectid"+projectId);
//		ModelAndView modelAndView = new ModelAndView();
//		getPreview(projectId, modelAndView);
//		modelAndView.setViewName("user/preview");
//		return modelAndView;
//	}
//	
//	@RequestMapping(value = "/user/projects", method = RequestMethod.GET)
//	public ModelAndView listUserProjects(Principal principal,HttpServletRequest httpServletRequest) {
//		ModelAndView model = new ModelAndView();
//		
//		List<Project> projects = projectService.listProjects(principal.getName());
//
//		model.addObject("myprojects", projects);
//		System.out.println("project "+projects);
//		// System.out.println("projects exist:" + projects);
//		model.setViewName("user/projects");
//		return model;
//
//	}
//	
//	@RequestMapping(value = "/user/offer/{id}", method = RequestMethod.GET)
//	public ModelAndView getOffer(@PathVariable("id") UUID projectId) {
////		System.out.println("user projectid"+projectId);
//		ModelAndView modelAndView = new ModelAndView();
//		getPreview(projectId, modelAndView);
//		modelAndView.setViewName("user/offer");
//		return modelAndView;
//	}
//	
//
//}
