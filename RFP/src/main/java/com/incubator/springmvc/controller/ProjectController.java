package com.incubator.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.google.gson.JsonObject;
import com.incubator.springmvc.billing.BillingProvider;
import com.incubator.springmvc.billing.StripeBilling;
import com.incubator.springmvc.dao.ProjectDAO;
import com.incubator.springmvc.model.Contact;
import com.incubator.springmvc.model.Invitation;
import com.incubator.springmvc.model.Project;
import com.incubator.springmvc.model.UploadedFile;
import com.incubator.springmvc.service.AccountService;
import com.incubator.springmvc.service.ContactService;
import com.incubator.springmvc.service.InvitationService;
import com.incubator.springmvc.service.ProjectService;
import com.incubator.springmvc.service.UploadedFileService;
import com.incubator.springmvc.utils.DeclareConstant;
import com.incubator.springmvc.utils.Utilities;
import com.incubator.springmvc.utils.UtilitiesS3;
import com.incubator.springmvc.validation.validators.TitleValidator;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

@Controller
@SessionAttributes("project")
public class ProjectController {

	private ProjectService projectService;
	private TitleValidator titleValidator;
	private ContactService contactService;
	private UploadedFileService uploadedFileService;
	private AccountService accountService;
	private InvitationService invitationService;



	@Autowired
	public void setInvitationService(InvitationService invitationService) {
		this.invitationService = invitationService;
	}

	@Autowired
	public void setTitleValidator(TitleValidator titleValidator) {
		this.titleValidator = titleValidator;
	}

	@Autowired
	public void setUploadedFileService(UploadedFileService uploadedFileService) {
		this.uploadedFileService = uploadedFileService;
	}

	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	@Autowired
	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@ModelAttribute("project")
	public Project createProjectBean() {
		return new Project();
	}

	@RequestMapping(value = { "/project" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView createProject(Principal principal, @ModelAttribute("project") Project project, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		if (!this.projectService.checkIfUserPaid()) {
			modelAndView.setViewName("/project/payment");
		}else{
			List<UploadedFile> filesList = (List<UploadedFile>) model.asMap().get("filesList");
			String imagePath = (String) model.asMap().get("imagePath");
			List<UploadedFile> imageFileList = this.uploadedFileService.listImage(UtilitiesS3.accountTempImageDirectory());
			if (null == filesList) {
				filesList = this.uploadedFileService.listFiles(UtilitiesS3.accountTempAttachDirectory());
				modelAndView.addObject("filesList", filesList);
			}
			if (imageFileList.size() > 0) {
				// imagePath = ((AmazonS3Client)UtilitiesS3.getS3Client())
				// .getResourceUrl(DeclareConstant.BUCKET,
				// UtilitiesS3.accountTempImageDirectory()+imageFileList.get(0).getName());
				// imagePath =
				// "https://s3.amazonaws.com/crowdfundingbucket/test/temp/img_1.jpg";
				// imagePath = "get/image/" +
				// UtilitiesS3.accountTempImageDirectory()+imageFileList.get(0).getName();
				imagePath = "get/image";

				modelAndView.addObject("imagePath", imagePath);
				modelAndView.addObject("imageFileList", imageFileList);
				System.out.println("imagepath " + imagePath);

			}
			modelAndView.setViewName("project");
			List<Contact> contacts = this.contactService.listContacts(principal.getName());

			// if (principal.getName().equals("grace@abc.com")) {
			// modelAndView.addObject("permissions", "ROLE_PCREATOR");
			// }

			String name = principal.getName();
			// System.out.println("project creator "+name);
			modelAndView.addObject("username", name);
			modelAndView.addObject("contactList", contacts);
			// System.out.println("utility " +
			// Utilities.getCurrentUserDetails().getId());
			UUID user_id = Utilities.getCurrentUserDetails().getId();
			modelAndView.addObject("user_id", user_id.toString());
		}



		return modelAndView;

	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// binder.setValidator(userValidator);
		binder.addValidators(titleValidator);
		binder.registerCustomEditor(UUID[].class, new UUIDEditor());
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PCREATOR')")
	@RequestMapping(value = "/project/add", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView addProject(@ModelAttribute("project") @Valid Project project, BindingResult result, Model model,
			Principal principal, RedirectAttributes redirectAttributes, SessionStatus status) {

		ModelAndView modelAndView = new ModelAndView();
		String imagePath = "";
		if (result.hasErrors()) {
			List<UploadedFile> imageFileList = this.uploadedFileService
					.listImage(UtilitiesS3.accountTempImageDirectory());
			List<UploadedFile> filesList = this.uploadedFileService.listFiles(UtilitiesS3.accountTempAttachDirectory());
			modelAndView.addObject("filesList", filesList);

			if (imageFileList.size() > 0) {
				imagePath = "get/image";
				modelAndView.addObject("imagePath", imagePath);
				modelAndView.addObject("imageFileList", imageFileList);
				// System.out.println("imagepath " + imagePath);

			}
			modelAndView.setViewName("/project");

			return modelAndView;
		}
		// status.setComplete();

		// System.out.println("project summary " + project);
		UUID projectId = this.projectService.addProject(project, principal);
		this.projectService.setAttachmentPath(projectId);
		modelAndView.setViewName("redirect:/project/projects");
		return modelAndView;
	}

	/**
	 * list projects of project creators
	 * @param principal
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/project/projects", method = RequestMethod.GET)
	public ModelAndView listProjects(Principal principal, HttpServletRequest httpServletRequest) {
		ModelAndView model = new ModelAndView();
		List<Project> projects = projectService.listProjectsForCreators(Utilities.getCurrentUserDetails().getId());
		model.addObject("myprojects", projects);
		System.out.println("project " + projects);
		// System.out.println("projects exist:" + projects);
		model.setViewName("projects");
		return model;

	}

	public void uploadAttachment(String path, String folder, HttpServletRequest request){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("file");
		UploadedFile uploadedFile = new UploadedFile();
		//		String path = UtilitiesS3.accountTempAttachDirectory();
		//		String folder = UtilitiesS3.accountTempAttachDirectoryWithoutSuffix();
		if (null != file) {

			try {

				uploadedFile.setLength(file.getBytes().length);
				uploadedFile.setName(file.getOriginalFilename());
				uploadedFile.setType(file.getContentType());
				uploadedFile.setBytes(file.getBytes());
				uploadedFile.setPath(path);
				uploadedFile.setRoleInProject(DeclareConstant.ATTACHMENTS);
				this.uploadedFileService.addFile(uploadedFile);

				UtilitiesS3.createFolder(DeclareConstant.BUCKET, folder, UtilitiesS3.getS3Client());

				File convertFile = UtilitiesS3.multipartToFile(file);
				UtilitiesS3.uploadFile(path + uploadedFile.getName(), convertFile);
				this.uploadedFileService.addFile(uploadedFile);

			} catch (Exception e) {
				e.printStackTrace();
				// return "File upload is failed: " + e.getMessage();
			}
		}
	}

	public void editUploadedAttachment(String path, String folder, UUID projectId, HttpServletRequest request){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("file");
		UploadedFile uploadedFile = new UploadedFile();
		//		String path = UtilitiesS3.accountTempAttachDirectory();
		//		String folder = UtilitiesS3.accountTempAttachDirectoryWithoutSuffix();
		System.out.println("file is null?"+file);
		if (null != file) {

			try {

				uploadedFile.setLength(file.getBytes().length);
				uploadedFile.setName(file.getOriginalFilename());
				uploadedFile.setType(file.getContentType());
				uploadedFile.setBytes(file.getBytes());
				uploadedFile.setPath(path);
				uploadedFile.setRoleInProject(DeclareConstant.ATTACHMENTS);
				//				this.uploadedFileService.addFile(uploadedFile);

				//				UtilitiesS3.createFolder(DeclareConstant.BUCKET, folder, UtilitiesS3.getS3Client());

				File convertFile = UtilitiesS3.multipartToFile(file);
				UtilitiesS3.uploadFile(path + uploadedFile.getName(), convertFile);
				this.uploadedFileService.addFileWithId(uploadedFile, projectId);

			} catch (Exception e) {
				e.printStackTrace();
				// return "File upload is failed: " + e.getMessage();
			}
		}
	}

	//	@RequestMapping(value = "/project/upload/attachment", method = { RequestMethod.POST })
	//	public String uploadAttachment(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
	//		if (!(request instanceof MultipartHttpServletRequest)) {
	//			System.out.println("multipartrequest" + (request instanceof MultipartHttpServletRequest));
	//			return "redirect:/project";
	//		} else {
	//			String path = UtilitiesS3.accountTempAttachDirectory();
	//			String folder = UtilitiesS3.accountTempAttachDirectoryWithoutSuffix();
	//			uploadAttachment(path, folder, request);
	//
	//			List<UploadedFile> filesList = this.uploadedFileService.listFiles(path);
	//			redirectAttributes.addFlashAttribute("filesList", filesList);
	//
	//			System.out.println("filelist" + filesList.size());
	//			return "redirect:/project";
	//		}
	//
	//	}

	@RequestMapping(value = "/project/upload/attachment", method = RequestMethod.GET)
	public String redirect() {

		return "redirect:/project";
		// return "project";
	}

	@RequestMapping(value = "/project/upload/image", method = RequestMethod.GET)
	public String redirectImage() {

		return "redirect:/project";
		// return "project";
	}

	@RequestMapping(value = { "/download/attachment/{id}" }, method = RequestMethod.GET)
	public void downloadDocument(@PathVariable UUID id, HttpServletResponse response) throws IOException {
		UploadedFile document = uploadedFileService.findById(id);
		String fileName = document.getPath() + document.getName();
		S3ObjectInputStream objectContent = UtilitiesS3.downLoadFile(fileName);
		// InputStream inputStream = FileUtils.openInputStream(downloadFile);
		response.setContentType(document.getType());
		response.setContentLength(document.getLength());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + document.getName() + "\"");

		// FileCopyUtils.copy(inputStream, response.getOutputStream());
		IOUtils.copy(objectContent, response.getOutputStream());

		response.getOutputStream().flush();
		response.getOutputStream().close();

		// return "project";
	}

	//	@RequestMapping(value = { "/delete/attachment/{id}" }, method = RequestMethod.GET)
	//	public String deleteDocument(@PathVariable UUID id) {
	//
	//		UploadedFile uploadedFile = this.uploadedFileService.findById(id);
	//		UtilitiesS3.deleteFile(uploadedFile.getPath() + uploadedFile.getName());
	//		// System.out.println("delete path " + uploadedFile.getPath() +
	//		// uploadedFile.getName());
	//		this.uploadedFileService.deleteById(id);
	//		return "redirect:/project";
	//	}


	public String uploadImage(String path, String folder, HttpServletRequest request) {
		if ((request instanceof MultipartHttpServletRequest)) {
			UploadedFile uploadedFile = new UploadedFile();
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

			MultipartFile file = multipartRequest.getFile("file");
			if (null != file ) {

				try {

					uploadedFile.setLength(file.getBytes().length);
					uploadedFile.setName(file.getOriginalFilename());
					uploadedFile.setType(file.getContentType());
					uploadedFile.setBytes(file.getBytes());
					uploadedFile.setPath(path);
					uploadedFile.setRoleInProject(DeclareConstant.IMAGE);

					UtilitiesS3.createFolder(DeclareConstant.BUCKET, folder, UtilitiesS3.getS3Client());

					/**
					 * Delete file information in database
					 */
					if (UtilitiesS3.listFileNames(path).size() > 0) {

						this.uploadedFileService.deleteByNameAndPath(UtilitiesS3.listFileNames(path).get(0), path);
						System.out
						.println("if delete name" + UtilitiesS3.listFileNames(path).get(0) + " path: " + path);
						UtilitiesS3.deleteFile(path + UtilitiesS3.listFileNames(path).get(0));
					}
					File convertFile = UtilitiesS3.multipartToFile(file);
					UtilitiesS3.uploadFile(path + uploadedFile.getName(), convertFile);
					// UtilitiesS3.setPublic(path+uploadedFile.getName());
					this.uploadedFileService.addFile(uploadedFile);

				} catch (Exception e) {
					e.printStackTrace();
					// return "File upload is failed: " + e.getMessage();
				}
				return "";
			}
			else{
				return "No file is chosen. Please try again!";
			}
		}
		else{
			return "Failed uploading the file. Please try again!";
		}

	}


	public String editUploadImage(String path, String folder, UUID projectId, UUID imageId, HttpServletRequest request) {

		if ((request instanceof MultipartHttpServletRequest)) {
			UploadedFile uploadedFile = new UploadedFile();
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

			MultipartFile file = multipartRequest.getFile("file");
			System.out.println("file "+file);
			if (null != file ) {

				try {

					uploadedFile.setId(imageId);
					uploadedFile.setLength(file.getBytes().length);
					uploadedFile.setName(file.getOriginalFilename());
					uploadedFile.setType(file.getContentType());
					uploadedFile.setBytes(file.getBytes());
					uploadedFile.setPath(path);
					uploadedFile.setRoleInProject(DeclareConstant.IMAGE);

					//					UtilitiesS3.createFolder(DeclareConstant.BUCKET, folder, UtilitiesS3.getS3Client());

					/**
					 * Edit file information in database
					 */
					if (UtilitiesS3.listFileNames(path).size() > 0) {

						//						this.uploadedFileService.deleteByNameAndPath(UtilitiesS3.listFileNames(path).get(0), path);
						System.out
						.println("if delete name" + UtilitiesS3.listFileNames(path).get(0) + " path: " + path);
						UtilitiesS3.deleteFile(path + UtilitiesS3.listFileNames(path).get(0));
					}
					File convertFile = UtilitiesS3.multipartToFile(file);
					UtilitiesS3.uploadFile(path + uploadedFile.getName(), convertFile);
					System.out.println("filename "+uploadedFile.getName());

					//					this.uploadedFileService.editUploadedImage(uploadedFile);
					// UtilitiesS3.setPublic(path+uploadedFile.getName());
					//					this.uploadedFileService.addFile(uploadedFile);
					//					System.out.println("null? "+this.uploadedFileService.findById(imageId).getName());
					if (null != this.uploadedFileService.findById(imageId).getName()) {
						this.uploadedFileService.editUploadedImage(uploadedFile);
						this.projectService.updateImageIdToProject(imageId, projectId);

					}else{
						this.uploadedFileService.addFileWithId(uploadedFile,projectId);
						this.projectService.updateImageIdToProject(this.uploadedFileService.getImageId(projectId), projectId);
						//	this.projectService.getProjectById(projectId).setImage(this.uploadedFileService.getImageId(projectId));
					}
					//					
					//	this.uploadedFileService.edit

				} catch (Exception e) {
					e.printStackTrace();
					// return "File upload is failed: " + e.getMessage();
				}
			}else{
				return "No file is chosen. Please try again!";
			}
		}else{
			return "Failed uploading the file. Please try again!";
		}
		return "";
	}
	//	@RequestMapping(value = "/project/upload/image", method = RequestMethod.POST)
	//	public ModelAndView uploadImage(HttpServletRequest request, @ModelAttribute("project") Project project, Model model,
	//			RedirectAttributes redirectAttributes) {
	//		ModelAndView modelAndView = new ModelAndView();
	//		System.out.println("project info " + project.getTitle());
	//		String path = UtilitiesS3.accountTempImageDirectory();
	//		String folder = UtilitiesS3.accountTempImageDirectoryWithoutSuffix();
	//		uploadImage(path, folder, request);
	//		String imagePath = "get/image";
	//		redirectAttributes.addFlashAttribute("imagePath", imagePath);
	//		// System.out.println("imagenotmultiple");
	//		modelAndView.setViewName("redirect:/project");
	//		// modelAndView.setViewName("project");
	//		// 2. send it back to the client as <img> that calls get method
	//		// we are using getTimeInMillis to avoid server cached image
	//
	//		/// * return "<img src='http://localhost:9090/RFP/get/" +
	//		/// Calendar.getInstance().getTimeInMillis()
	//		// + "' width='304' height='236' />";
	//		return modelAndView;
	//	}



	@RequestMapping(value = "/get/image", method = RequestMethod.GET)
	public @ResponseBody byte[] getImage(HttpServletRequest request, HttpServletResponse response) {
		byte[] imageBytes = {};
		List<UploadedFile> imageFileList = this.uploadedFileService.listImage(UtilitiesS3.accountTempImageDirectory());
		String fileName = UtilitiesS3.accountTempImageDirectory() + imageFileList.get(0).getName();
		imageBytes = UtilitiesS3.getFileBytes(fileName);
		response.setContentType("image/jpeg");
		response.setContentLength(imageBytes.length);
		// }

		return imageBytes;
	}

	@RequestMapping(value = "/get/image/{id}", method = RequestMethod.GET)
	public @ResponseBody byte[] getImagePreview(@PathVariable("id") UUID projectId, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("get?");
		byte[] imageBytes = {};
		String path = UtilitiesS3.getAccountImageDirectory(this.accountService.getCreatorIdFromProjectId(projectId),
				projectId);
		//		List<UploadedFile> files = this.uploadedFileService.listFiles(path);
		UUID imageId = this.projectService.getProjectById(projectId).getImageId();
		UploadedFile file = this.uploadedFileService.findById(imageId);
		String imageName = this.uploadedFileService.findById(imageId).getName();
		//		System.out.println("size " + files.size());
		if (null != file.getName()) {
			//			System.out.println("file "+file.getName());
			String fileName = path + imageName;
			System.out.println("file name " + fileName);
			imageBytes = UtilitiesS3.getFileBytes(fileName);

			response.setContentType("image/jpeg");
			response.setContentLength(imageBytes.length);
		}

		return imageBytes;
	}

	public void getPreview(UUID projectId, ModelAndView modelAndView) {
		Project project = this.projectService.getProjectById(projectId);
		modelAndView.addObject("project", project);
		//		UUID creatorId = this.accountService.getCreatorIdFromProjectId(projectId);
		List<UploadedFile> imageFileList = new ArrayList<>();

		UUID imageId = this.projectService.getProjectById(projectId).getImageId();
		UploadedFile file = this.uploadedFileService.findById(imageId);
		file.setId(imageId);
		String path = "get/image/" + projectId;
		if (null != file.getName()) {
			//			System.out.println("file is null"+file.getId());FV
			//			file.setId(imageId);
			imageFileList.add(file);
			modelAndView.addObject("imagePath", path);
			modelAndView.addObject("imageFileList", imageFileList);

		}



		//		if (imageFileList.size() > 0) {
		//			modelAndView.addObject("imagePath", path);
		//			modelAndView.addObject("imageFileList", imageFileList);
		//
		//		}


		//		List<UploadedFile> filesList = this.uploadedFileService
		//				.listFiles(UtilitiesS3.getAccountAttachDirectory(creatorId, projectId));
		List<UploadedFile> filesList = this.uploadedFileService.listEditedFiles(projectId);
		modelAndView.addObject("filesList", filesList);

	}

	@RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
	public ModelAndView getProjectById(@PathVariable("id") UUID projectId) {
		ModelAndView modelAndView = new ModelAndView();
		getPreview(projectId, modelAndView);
		modelAndView.setViewName("project/preview");
		return modelAndView;
	}

	/**
	 * view the project by admin
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/admin/project/{id}/{userId}", method = RequestMethod.GET)
	public ModelAndView adminGetProjectById(@PathVariable("id") UUID projectId, @PathVariable String userId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userId", userId);
		getPreview(projectId, modelAndView);
		modelAndView.setViewName("/admin/preview");
		return modelAndView;
	}
	@RequestMapping(value = "/{summary}/{id}", method = RequestMethod.GET)
	public ModelAndView getPreviewWithSummaryForUser(@PathVariable("id") UUID projectId,
			@PathVariable("summary") String summary) {
		// System.out.println("user projectid"+projectId);
		ModelAndView modelAndView = new ModelAndView();
		getPreview(projectId, modelAndView);
		modelAndView.setViewName("user/preview");
		modelAndView.addObject("summary", "summary");
		return modelAndView;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView getPreviewForUser(@PathVariable("id") UUID projectId) {
		// System.out.println("user projectid"+projectId);
		ModelAndView modelAndView = new ModelAndView();
		getPreview(projectId, modelAndView);
		modelAndView.setViewName("user/preview");
		return modelAndView;
	}



	/**
	 * offer controller
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/user/offer/{id}", method = RequestMethod.GET)
	public ModelAndView getOffer(@PathVariable("id") String projectId) {
		// System.out.println("user projectid"+projectId);
		ModelAndView modelAndView = new ModelAndView();
		getPreview(UUID.fromString(projectId), modelAndView);
		modelAndView.addObject("projectId", projectId);
		modelAndView.setViewName("user/offer");
		return modelAndView;
	}

	@RequestMapping(value = "/user/getTerms/{id}", method = RequestMethod.GET)
	public @ResponseBody String getTerms(@PathVariable("id") UUID projectId) {
		// System.out.println("user projectid"+projectId);
		Invitation invitation = this.invitationService.getInvitationById(projectId);
		String terms = invitation.getThreshold();

		return terms;
	}


	@RequestMapping(value = "/user/projects", method = RequestMethod.GET)
	public ModelAndView listUserProjects(Principal principal) {
		ModelAndView model = new ModelAndView();
		UUID userId = this.accountService.getIdByAuthorityAndEmail(principal.getName(), DeclareConstant.ROLE_USER);
		List<Invitation> projects = projectService.listProjectsForUsers(userId);
		model.addObject("myprojects", projects);
		model.setViewName("user/projects");
		return model;

	}
	/**
	 * list project of users
	 * it also changed offer status
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/user/projects/{projectId}", method = RequestMethod.GET)
	public ModelAndView listUserProjects(@PathVariable UUID projectId,Principal principal) {
		ModelAndView model = new ModelAndView();
		UUID userId = this.accountService.getIdByAuthorityAndEmail(principal.getName(), DeclareConstant.ROLE_USER);

		System.out.println("updated?" +projectId+" "+userId);
		this.invitationService.updateOfferStatus(DeclareConstant.ACCEPTOFFER, projectId, userId);
		List<Invitation> projects = projectService.listProjectsForUsers(userId);
		model.addObject("myprojects", projects);
		model.setViewName("user/projects");
		return model;

	}

	/**
	 * payment controller
	 */

	@RequestMapping(value = "/project/payment", method = RequestMethod.GET)
	public String enterPaymentPage() {

		return "/project/payment";
	}

	@RequestMapping(value = "/account/stripe_card_token", method = RequestMethod.POST)
	@ResponseBody
	public String getPaymentToken(@RequestParam String token, Principal principal) {
		String response = "";
		// Set your secret key: remember to change this to your live secret key
		// in production
		// See your keys here https://dashboard.stripe.com/account/apikeys
		// ModelAndView modelAndView = new ModelAndView();
		BillingProvider billingProvider = new StripeBilling();
		String result = billingProvider.charge(token);
		//	System.out.println("payment result "+result);
		if (!result.equals(DeclareConstant.DECLINED)) {

			// response = "/project";
			response = DeclareConstant.SUCCESS;
			this.projectService.addPayment(Utilities.getCurrentUserDetails().getId(),principal.getName(),DeclareConstant.PENDING, result,token);

			// modelAndView.setViewName("/project");
		} else  {
			// modelAndView.setViewName("/account/stripe_card_token");
			// response = "/account/stripe_card_token";
			// return "/account/stripe_card_token";
			response = DeclareConstant.DECLINED;
		}
		System.out.println("token: " + token);
		return response;
	}

	/**
	 * delete a project
	 */

	@RequestMapping(value = { "/project/delete/{id}" }, method = RequestMethod.GET)
	public String deleteProject(@PathVariable("id") UUID projectId) {
		UUID imageId = this.uploadedFileService.getImageId(projectId);
		if (null != imageId) {
			UploadedFile uploadedFile = this.uploadedFileService.findById(imageId);
			UtilitiesS3.deleteFile(uploadedFile.getPath() + uploadedFile.getName());
			this.uploadedFileService.deleteById(imageId);
		}

		// // System.out.println("delete path " + uploadedFile.getPath() +
		// uploadedFile.getName());
		List<UUID> attachmentIds = this.uploadedFileService.getAttachmentIds(projectId);
		if (!attachmentIds.isEmpty()) {
			for (UUID uuid : attachmentIds) {
				UploadedFile uploadedFile = this.uploadedFileService.findById(imageId);
				UtilitiesS3.deleteFile(uploadedFile.getPath() + uploadedFile.getName());
				this.uploadedFileService.deleteById(uuid);
			}

		}

		this.projectService.deleteAccountsProjectsById(projectId);
		this.projectService.deleteProjectById(projectId);

		return "redirect:/project/projects";
	}

	/**
	 * edit the project
	 */
	@RequestMapping(value = { "/project/edit/{id}" }, method = RequestMethod.GET)
	public ModelAndView editProject(@PathVariable("id") UUID projectId) {
		ModelAndView modelAndView = new ModelAndView();
		getPreview(projectId, modelAndView);
		Project project = this.projectService.getProjectById(projectId);
		modelAndView.addObject("project", project);
		//	System.out.println("edit project"+project);
		modelAndView.setViewName("/project/EditProject");
		return modelAndView;
	}

	//	@RequestMapping(value = "/project/edit/upload/image/{id}", method = RequestMethod.POST)
	//	public ModelAndView editUploadImage(@PathVariable("id") UUID projectId, HttpServletRequest request, @ModelAttribute("project") Project project, Model model,
	//			RedirectAttributes redirectAttributes) {
	//		ModelAndView modelAndView = new ModelAndView();
	//		//		System.out.println("project info " + project.getTitle());
	//		String path = UtilitiesS3.getAccountImageDirectory(Utilities.getCurrentUserDetails().getId(), projectId);
	//		String folder = UtilitiesS3.getAccountImageDirectoryWithoutSuffix(Utilities.getCurrentUserDetails().getId(), projectId);
	//		UUID imageId = this.projectService.getProjectById(projectId).getImage();
	//		editUploadImage(path, folder, projectId, imageId, request);
	//
	//		String imagePath = "get/image"+"/"+projectId;
	//
	//		//		redirectAttributes.addFlashAttribute("imagePath", imagePath);
	//		// System.out.println("imagenotmultiple");
	//		modelAndView.addObject("imagePath", imagePath);
	//		modelAndView.setViewName("redirect:/project/edit/"+projectId);
	//		// modelAndView.setViewName("project");
	//		// 2. send it back to the client as <img> that calls get method
	//		// we are using getTimeInMillis to avoid server cached image
	//
	//		/// * return "<img src='http://localhost:9090/RFP/get/" +
	//		/// Calendar.getInstance().getTimeInMillis()
	//		// + "' width='304' height='236' />";
	//		return modelAndView;
	//	}

	//	@RequestMapping(value = { "/edit/delete/image/{projectId}/{imageId}" }, method = RequestMethod.GET)
	//	public String editDeleteImage(@PathVariable UUID projectId,@PathVariable UUID imageId) {
	//
	//		UploadedFile uploadedFile = this.uploadedFileService.findById(imageId);
	//		UtilitiesS3.deleteFile(uploadedFile.getPath() + uploadedFile.getName());
	//		// System.out.println("delete path " + uploadedFile.getPath() +
	//		// uploadedFile.getName());
	//		this.uploadedFileService.deleteById(imageId);
	//		//		this.uploadedFileService.updateImageId(projectId, imageId);
	//		//		String path = "/project/edit/"+projectId;
	//		return "redirect:/project/edit/"+projectId;
	//	}

	//	@RequestMapping(value = { "/edit/delete/attachment/{projectId}/{fileId}" }, method = RequestMethod.GET)
	//	public String editDeleteDocument(@PathVariable UUID projectId,@PathVariable UUID fileId) {
	//
	//		UploadedFile uploadedFile = this.uploadedFileService.findById(fileId);
	//		UtilitiesS3.deleteFile(uploadedFile.getPath() + uploadedFile.getName());
	//		// System.out.println("delete path " + uploadedFile.getPath() +
	//		// uploadedFile.getName());
	//		this.uploadedFileService.deleteById(fileId);
	//		return "redirect:/project/edit/"+projectId;
	//	}

	//	@RequestMapping(value = "/project/edit/upload/attachment/{projectId}", method = { RequestMethod.POST })
	//	public String editUploadAttachment(@PathVariable UUID projectId, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
	//		if (!(request instanceof MultipartHttpServletRequest)) {
	//			//			System.out.println("multipartrequest" + (request instanceof MultipartHttpServletRequest));
	//			return "redirect:/project/edit/"+projectId;
	//		} else {
	//			String path = UtilitiesS3.getAccountAttachDirectory(Utilities.getCurrentUserDetails().getId(), projectId);
	//			String folder = UtilitiesS3.getAccountAttachDirectoryWithoutSuffix(Utilities.getCurrentUserDetails().getId(), projectId);
	//			editUploadedAttachment(path, folder, projectId, request);
	//
	//			//			List<UploadedFile> filesList = this.uploadedFileService.listFiles(path);
	//			List<UploadedFile> filesList = this.uploadedFileService.listEditedFiles(projectId);
	//			redirectAttributes.addFlashAttribute("filesList", filesList);
	//
	//			System.out.println("filelist" + filesList.size());
	//			return "redirect:/project/edit/"+projectId;
	//		}
	//
	//	}

	@RequestMapping(value = "/project/update/{projectId}", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView editProject(@PathVariable UUID projectId, @ModelAttribute("project") @Valid Project project, BindingResult result, Model model,
			Principal principal, RedirectAttributes redirectAttributes, SessionStatus status) {

		System.out.println("project summary " +project);
		ModelAndView modelAndView = new ModelAndView();
		//		String imagePath = "";
		//		if (result.hasErrors()) {
		////			List<UploadedFile> imageFileList = this.uploadedFileService
		////					.listImage(UtilitiesS3.getAccountImageDirectory(Utilities.getCurrentUserDetails().getId(), projectId));
		////			List<UploadedFile> filesList = this.uploadedFileService
		////					.listFiles(UtilitiesS3.getAccountAttachDirectory(Utilities.getCurrentUserDetails().getId(), projectId));
		////			modelAndView.addObject("filesList", filesList);
		////
		////			if (imageFileList.size() > 0) {
		////				imagePath = "get/image";
		////				modelAndView.addObject("imagePath", imagePath);
		////				modelAndView.addObject("imageFileList", imageFileList);
		////				// System.out.println("imagepath " + imagePath);
		////
		////			}
		//			modelAndView.setViewName("/project/edit/"+projectId);
		//			System.out.println("project summary 1" + project);
		//			return modelAndView;
		//		}
		// status.setComplete();

		System.out.println("project summary 2" + project);
		this.projectService.updateProject(project);
		//	this.projectService.setAttachmentPath(projectId);
		modelAndView.setViewName("redirect:/project/projects");
		return modelAndView;
	}

	/**
	 * list projects by admin
	 */
	@RequestMapping(value = "/admin/projects/{userId}", method = RequestMethod.GET)
	public ModelAndView listProjects(@PathVariable String userId, HttpServletRequest httpServletRequest) {
		ModelAndView model = new ModelAndView();
		List<Project> projects = projectService.listProjectsForCreators(UUID.fromString(userId));
		model.addObject("myprojects", projects);
		model.addObject("userId",userId);
		System.out.println("project " + projects);
		// System.out.println("projects exist:" + projects);
		model.setViewName("/admin/projects");
		return model;

	}

	/**
	 * list projects by user id and their permission by admin
	 */
	@RequestMapping(value = "/admin/projects/{userId}/{permission}", method = RequestMethod.GET)
	public ModelAndView listProjectsByUserIdAndPermission(@PathVariable String userId, @PathVariable String permission) {
		ModelAndView model = new ModelAndView();



		// System.out.println("projects exist:" + projects);
		if (permission.equals(DeclareConstant.ROLE_USER)) {
			model.setViewName("/admin/userProjects");
			List<Invitation> projects = projectService.listProjectsForUsers(UUID.fromString(userId));
			model.addObject("myprojects", projects);
		}else if (permission.equals(DeclareConstant.ROLE_PCREATOR)) {
			List<Project> projects = projectService.listProjectsForCreators(UUID.fromString(userId));
			model.addObject("myprojects", projects);
			model.setViewName("/admin/projects");
		}
		//	System.out.println("project " + projects);
		model.addObject("userId",userId);
		return model;

	}

	//TODO project edit needs to be processed
	/**
	 * edit project by admin
	 */
	@RequestMapping(value = { "admin/editProject/{projectId}/{userId}" }, method = RequestMethod.GET)
	public ModelAndView editProjectByAdmin(@PathVariable String projectId, @PathVariable String userId) {
		ModelAndView modelAndView = new ModelAndView();
		getPreview(UUID.fromString(projectId), modelAndView);
		Project project = this.projectService.getProjectById(UUID.fromString(projectId));
		modelAndView.addObject("project", project);
		modelAndView.addObject("projectId", projectId);
		modelAndView.addObject("userId", userId);
		//	System.out.println("edit project"+project);
		modelAndView.setViewName("/admin/EditProject");
		return modelAndView;
	}

	/**
	 * display image when editing the project
	 * @param request
	 * @param response
	 * @param value
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "/get/{projectId}/{value}", method = RequestMethod.GET)
	public @ResponseBody byte[] getImageBytes(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable String value, @PathVariable UUID projectId) {
		//		System.out.println("get?");
		byte[] imageBytes = {};
		String path = UtilitiesS3.getAccountImageDirectory(this.accountService.getCreatorIdFromProjectId(projectId),
				projectId);
		//		List<UploadedFile> files = this.uploadedFileService.listFiles(path);
		UUID imageId = this.projectService.getProjectById(projectId).getImageId();
		UploadedFile file = this.uploadedFileService.findById(imageId);
		String imageName = this.uploadedFileService.findById(imageId).getName();
		//		System.out.println("size " + files.size());
		if (null != file.getName()) {
			//			System.out.println("file "+file.getName());
			String fileName = path + imageName;
			System.out.println("file name " + fileName);
			imageBytes = UtilitiesS3.getFileBytes(fileName);
			System.out.println("bytes "+imageBytes);

			response.setContentType("image/jpeg");
			response.setContentLength(imageBytes.length);
		}

		return imageBytes;
	}

	/**
	 * display the image when creating the project
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
	public @ResponseBody byte[] displayUploadedImage(HttpServletRequest request, HttpServletResponse response) {
		byte[] imageBytes = {};
		List<UploadedFile> imageFileList = this.uploadedFileService.listImage(UtilitiesS3.accountTempImageDirectory());
		String fileName = UtilitiesS3.accountTempImageDirectory() + imageFileList.get(0).getName();
		imageBytes = UtilitiesS3.getFileBytes(fileName);
		response.setContentType("image/jpeg");
		response.setContentLength(imageBytes.length);

		return imageBytes;
	}

	/**
	 * use jquery form to upload image when editing the project
	 */
	@RequestMapping(value = "/project/edit/upload/image/{id}", method = RequestMethod.POST)
	public @ResponseBody String editUploadImage(@PathVariable("id") UUID projectId, MultipartHttpServletRequest request, HttpServletResponse response) {
		//		ModelAndView modelAndView = new ModelAndView();
		//		System.out.println("project info " + project.getTitle());
		String path = UtilitiesS3.getAccountImageDirectory(Utilities.getCurrentUserDetails().getId(), projectId);
		String folder = UtilitiesS3.getAccountImageDirectoryWithoutSuffix(Utilities.getCurrentUserDetails().getId(), projectId);
		UUID imageId = this.projectService.getProjectById(projectId).getImageId();
		String error=editUploadImage(path, folder, projectId, imageId, request);
		if(error.isEmpty()){
			return "<img src='"+Utilities.getURLWithContextPath(request)+"/get/"+projectId+"/"+Calendar.getInstance().getTimeInMillis()+"' class='col-md-10, col-sm-12'/>";

		}else{
			return "<div class='alert alert-danger'><strong>"+error+"</strong></div>";
		}

		//		String imagePath = "get/image"+"/"+projectId;

		//		redirectAttributes.addFlashAttribute("imagePath", imagePath);
		// System.out.println("imagenotmultiple");
		//		modelAndView.addObject("imagePath", imagePath);
		//		modelAndView.setViewName("redirect:/project/edit/"+projectId);
		// modelAndView.setViewName("project");
		// 2. send it back to the client as <img> that calls get method
		// we are using getTimeInMillis to avoid server cached image

		/// * return "<img src='http://localhost:9090/RFP/get/" +
		/// Calendar.getInstance().getTimeInMillis()
		// + "' width='304' height='236' />";
	}

	/**
	 * use jquery form to upload image when creating the project
	 */
	@RequestMapping(value = "/project/upload/image", method = RequestMethod.POST)
	public @ResponseBody String uploadImage(MultipartHttpServletRequest request, @ModelAttribute("project") Project project) {
		//		ModelAndView modelAndView = new ModelAndView();
		System.out.println("project info " + project.getTitle());
		String path = UtilitiesS3.accountTempImageDirectory();
		String folder = UtilitiesS3.accountTempImageDirectoryWithoutSuffix();
		String error = uploadImage(path, folder, request);
		if(error.isEmpty()){
			return "<img src='"+Utilities.getURLWithContextPath(request)+"/get/"+Calendar.getInstance().getTimeInMillis()+"' class='col-md-10, col-sm-12'/>";

		}else{
			return "<div class='alert alert-danger'><strong>"+error+"</strong></div>";
		}
		//		String imagePath = "get/image";
		//		redirectAttributes.addFlashAttribute("imagePath", imagePath);
		//		modelAndView.setViewName("redirect:/project");

	}
	public JSONObject constructJSONAttachments(List<UploadedFile> filesList){
		//		List<UploadedFile> filesList = this.uploadedFileService.listEditedFiles(projectId);
		JSONObject jsonObject = new JSONObject();
		List<JSONObject> innerObjects = new ArrayList<>();
		for (UploadedFile uploadedFile : filesList) {
			JSONObject innerJsonObject = new JSONObject();
			innerJsonObject.put("fileId", uploadedFile.getId());
			innerJsonObject.put("filename", uploadedFile.getName());
			innerObjects.add(innerJsonObject);
		}
		jsonObject.put("files", innerObjects);
		return jsonObject;
	}
	/**
	 * delete the file using ajax
	 */
	@RequestMapping(value = { "/edit/delete/image/{projectId}" }, method = RequestMethod.GET)
	public @ResponseBody String editDeleteImage(@PathVariable UUID projectId) {

		UUID imageId = this.projectService.getProjectById(projectId).getImageId();
		UploadedFile uploadedFile = this.uploadedFileService.findById(imageId);
		UtilitiesS3.deleteFile(uploadedFile.getPath() + uploadedFile.getName());
		// System.out.println("delete path " + uploadedFile.getPath() +
		// uploadedFile.getName());
		this.uploadedFileService.deleteById(imageId);
		this.projectService.updateImageIdToProject(null, projectId);
		//		this.uploadedFileService.updateImageId(projectId, imageId);
		//		String path = "/project/edit/"+projectId;
		return "";
	}
	/**
	 * delete the file using ajax when creating the project
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "/delete/attachment/{id}" }, method = RequestMethod.GET)
	public @ResponseBody JSONObject deleteDocument(@PathVariable UUID id) {

		UploadedFile uploadedFile = this.uploadedFileService.findById(id);
		UtilitiesS3.deleteFile(uploadedFile.getPath() + uploadedFile.getName());
		// System.out.println("delete path " + uploadedFile.getPath() +
		// uploadedFile.getName());
		this.uploadedFileService.deleteById(id);
		String path = UtilitiesS3.accountTempAttachDirectory();
		List<UploadedFile> filesList = this.uploadedFileService.listFiles(path);
		JSONObject jsonObject = constructJSONAttachments(filesList);
		System.out.println("jsonobejcts "+jsonObject);
		return jsonObject;
	}

	/**
	 * delete image when creating the project
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "/delete/image" }, method = RequestMethod.GET)
	public @ResponseBody String deleteImage() {

		List<UploadedFile> imageFileList = this.uploadedFileService.listImage(UtilitiesS3.accountTempImageDirectory());
		//		System.out.println("path "+UtilitiesS3.accountTempImageDirectory());
		//		System.out.println("name "+imageFileList.get(0).getName());
		//		System.out.println("id "+imageFileList.get(0).getId());
		UUID imageId = null;
		if (imageFileList.size()>0) {
			imageId = imageFileList.get(0).getId();
			UtilitiesS3.deleteFile(UtilitiesS3.accountTempImageDirectory() + imageFileList.get(0).getName());
			this.uploadedFileService.deleteById(imageId);
		}
		//		UploadedFile uploadedFile = this.uploadedFileService.findById(imageId);

		// System.out.println("delete path " + uploadedFile.getPath() +
		// uploadedFile.getName());

		return "";
	}

	/**
	 * upload attachments using ajax when editing the project
	 */

	@RequestMapping(value = "/project/edit/upload/attachment/{projectId}", method = RequestMethod.POST,produces = "application/json")
	public @ResponseBody JSONObject editUploadAttachment(@PathVariable UUID projectId, MultipartHttpServletRequest request, HttpServletResponse response) {
		//		if (!(request instanceof MultipartHttpServletRequest)) {
		//			return "redirect:/project/edit/"+projectId;
		//		} else {
		System.out.println("anythings");
		String path = UtilitiesS3.getAccountAttachDirectory(Utilities.getCurrentUserDetails().getId(), projectId);
		String folder = UtilitiesS3.getAccountAttachDirectoryWithoutSuffix(Utilities.getCurrentUserDetails().getId(), projectId);
		editUploadedAttachment(path, folder, projectId, request);
		List<UploadedFile> filesList = this.uploadedFileService.listEditedFiles(projectId);
		//			List<UploadedFile> filesList = this.uploadedFileService.listEditedFiles(projectId);
		//			JSONObject jsonObject = new JSONObject();
		//			List<JSONObject> innerObjects = new ArrayList<>();
		//			for (UploadedFile uploadedFile : filesList) {
		//				JSONObject innerJsonObject = new JSONObject();
		//				innerJsonObject.put("fileId", uploadedFile.getId());
		//				innerJsonObject.put("filename", uploadedFile.getName());
		//				innerObjects.add(innerJsonObject);
		//			}
		//			jsonObject.put("files", innerObjects);
		JSONObject jsonObject = constructJSONAttachments(filesList);
		System.out.println("jsonObject" + jsonObject);
		return jsonObject;
		//		return "redirect:/project/edit/"+projectId;
		//		}

	}

	/**
	 * upload attachments using ajax when creating the project
	 */
	@RequestMapping(value = "/project/upload/attachment", method = { RequestMethod.POST },produces = "application/json")
	public @ResponseBody JSONObject uploadAttachment(MultipartHttpServletRequest request) {
		//		if (!(request instanceof MultipartHttpServletRequest)) {
		//			System.out.println("multipartrequest" + (request instanceof MultipartHttpServletRequest));
		//			return "redirect:/project";
		//		} else {
		String path = UtilitiesS3.accountTempAttachDirectory();
		String folder = UtilitiesS3.accountTempAttachDirectoryWithoutSuffix();
		uploadAttachment(path, folder, request);

		List<UploadedFile> filesList = this.uploadedFileService.listFiles(path);
		//			redirectAttributes.addFlashAttribute("filesList", filesList);
		JSONObject jsonObject = constructJSONAttachments(filesList);
		//			System.out.println("filelist" + filesList.size());
		return jsonObject;
		//		}

	}
	/**
	 * delete uploaded attachments using ajax in edit
	 * @param projectId
	 * @param fileId
	 * @return
	 */
	@RequestMapping(value = { "/edit/delete/attachment/{projectId}/{fileId}" }, method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody JSONObject editDeleteDocument(@PathVariable UUID projectId,@PathVariable UUID fileId,HttpServletResponse response) {

		UploadedFile uploadedFile = this.uploadedFileService.findById(fileId);
		UtilitiesS3.deleteFile(uploadedFile.getPath() + uploadedFile.getName());
		// System.out.println("delete path " + uploadedFile.getPath() +
		// uploadedFile.getName());
		this.uploadedFileService.deleteById(fileId);
		List<UploadedFile> filesList = this.uploadedFileService.listEditedFiles(projectId);
		JSONObject jsonObject = constructJSONAttachments(filesList);
		System.out.println("jsonobejcts "+jsonObject);
		//return "redirect:/project/edit/"+projectId;
		return jsonObject;
	}
}
