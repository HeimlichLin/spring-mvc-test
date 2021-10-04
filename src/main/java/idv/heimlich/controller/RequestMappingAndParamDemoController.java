package idv.heimlich.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(value = "/demo")
@Controller
public class RequestMappingAndParamDemoController {

	private static Logger LOGGER = LoggerFactory.getLogger(RequestMappingAndParamDemoController.class);

	@RequestMapping(value = "/home")
	public String home() {
		return "requestMappingAndParamHome";
	}

	// test 1: Testing @RequestParam without explicit attributes
	@RequestMapping(value = "/test1")
	public String requestMappongAndParamTest1(@RequestParam("orgname") String orgName, Model model) {
		model.addAttribute("orgname", orgName);
		model.addAttribute("testserial", "test1");
		return "requestMappingAndParamResults";
	}

	// test 2: Testing @RequestMapping 'method' attribute
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public String requestMappingAndParamTest2(@RequestParam("orgname") String orgName, Model model) {
		model.addAttribute("orgname", orgName);
		model.addAttribute("testserial", "test2");
		return "requestMappingAndParamResults";
	}

	// test 3: Testing @RequestMapping fall back feature
	@RequestMapping(value = "*", method = RequestMethod.GET)
	// @RequestMapping(value="*", method = {RequestMethod.GET,
	// RequestMethod.POST})
	public String requestMappingAndParamTest3() {
		return "fallback";
	}

	// test 4: Testing @RequestParam 'defaultValue' attribute
	@RequestMapping(value = "/test4")
	public String requestMappingAndParamTest4(
			@RequestParam(value = "orgname", defaultValue = "Anonymous Org") String orgName, Model model) {
		model.addAttribute("orgname", orgName);
		model.addAttribute("testserial", "test4");
		return "requestMappingAndParamResults";
	}

	// test 5: Testing @RequestMapping without 'name' or 'value' attributes
	@RequestMapping(value = "/test5", method = RequestMethod.GET)
	public String requestMappingAndParamTest5(@RequestParam String orgname, Model model) {
		model.addAttribute("orgname", orgname);
		model.addAttribute("testserial", "test5");
		return "requestMappingAndParamResults";
	}

	// test 6, subtest 1: Testing @RequestMapping
	// @RequestMapping(value = "/test6")
	// public String requestMappingAndParamTest6Subtest1(
	// @RequestParam String orgname, Model model) {
	// model.addAttribute("orgname", orgname);
	// model.addAttribute("testserial", "test6-subtest1");
	// return "requestMappingAndParamResults2";
	// }

	// test 6, subtest 1: Testing @RequestMapping
	// @RequestMapping(value = "/test6")
	// public String requestMappingAndParamTest6Subtest2(
	// @RequestParam String empcount, Model model) {
	// model.addAttribute("orgname", empcount);
	// model.addAttribute("testserial", "test6-subtest2");
	// return "requestMappingAndParamResults2";
	// }

	// test 6, subtest 1: Testing @RequestMapping with the same base URI
	// but whith different parameter
	@RequestMapping(value = "/test6", params = "orgname")
	public String requestMappingAndParamTest6Subtest1(@RequestParam String orgname, Model model) {
		model.addAttribute("orgname", orgname);
		model.addAttribute("testserial", "test6-subtest1");
		return "requestMappingAndParamResults2";
	}

	// test 6, subtest 2: Testing @RequestMapping with the same base URI
	// but whith different parameter
	@RequestMapping(value = "/test6", params = "empcount")
	public String requestMappingAndParamTest6Subtest2(@RequestParam String empcount, Model model) {
		model.addAttribute("orgname", empcount);
		model.addAttribute("testserial", "test6-subtest2");
		return "requestMappingAndParamResults2";
	}

	// test 6, subtest 3: Testing @RequestMapping with the multiple parameter
	@RequestMapping(value = "/test6/subtest3", method = RequestMethod.GET, params = { "orgname", "empcount" })
	public String requestMappingAndParamTest6Subtest3(@RequestParam String orgname, @RequestParam String empcount,
			Model model) {
		model.addAttribute("orgname", orgname);
		model.addAttribute("empcount", empcount);
		model.addAttribute("testserial", "test6-subtest3");
		return "requestMappingAndParamResults2";
	}

	// test 6, subtest 4: Testing @RequestMapping with the single parameter
	@RequestMapping(value = "/test6/subtest4", method = RequestMethod.GET, params = { "orgname", "empcount" })
	public String requestMappingAndParamTest6Subtest4(@RequestParam String orgname, Model model) {
		model.addAttribute("orgname", orgname);
		model.addAttribute("testserial", "test6-subtest4");
		return "requestMappingAndParamResults2";
	}

	// test 7 & 8: Testing @RequestMapping with the multiple URI's
	@RequestMapping(value = { "/test7", "/test8" }, method = RequestMethod.GET)
	public String requestMappingAndParamTest7and8(@RequestParam String orgname, Model model,
			HttpServletRequest request) {
		model.addAttribute("orgname", orgname);
		LOGGER.info(request.getRequestURL().toString());
		if (request.getRequestURL().toString().contains("test7")) {
			model.addAttribute("testserial", "test7");
		} else if (request.getRequestURL().toString().contains("test8")) {
			model.addAttribute("testserial", "test8");
		}
		return "requestMappingAndParamResults2";
	}

	// 接收檔案，@RequestParam("fileName") MultipartFile[] files or MultipartFile
	// file
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String requestMappingAndParam(@RequestParam("fileName") MultipartFile[] files, Model model) {
		if (null != files && files.length > 0) {
			//遍歷並儲存檔案
			for (final MultipartFile file : files) {
				try {
					file.transferTo(new File("C:\\Users\\6284\\Desktop\\123.txt"));
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}

//		if (null != files && files.length > 0) {
//			for (final MultipartFile file : files) {
//				// 取得檔案類型
//				final String contentType = file.getContentType();
//				if (!contentType.equals("")) {
//					// 可以加入檔案類型檢核
//				}
//				// 取得檔名
//				final String name = file.getName();
//				// 取得檔案全名
//				final String originFileName = file.getOriginalFilename();
//				// 取得檔案副檔名
//				final String extension = originFileName.substring(originFileName.lastIndexOf("."));
//				System.out.println(extension);
//				// 取得檔案大小
//				final long site = file.getSize();
//				if (site > MAX_FILE_SISE) {
//					//可以對檔案大小進行過濾
//				}
//				// 文件上傳後的絕對路徑，此處取系統時間合併文件名作為文件名
//				// 不推薦，會有同時上傳，檔名衝突的問題
//				final String fileName = UPLOAD_DIR + String.valueOf(System.currentTimeMillis()) + extension;
//				try {
//					file.transferTo(new File(fileName));
//				} catch (final Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
		return "requestMappingAndParamHome";
	}

}
