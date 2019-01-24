package company.inov.strategy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import company.inov.strategy.dao.PerspectiveDao;
import company.inov.strategy.model.Perspective;
import company.inov.strategy.response.BaseResponse;

@RestController
@RequestMapping("/perspective")
public class PerspectiveController {

	@Autowired
	PerspectiveDao perpectiveDao;

	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	private BaseResponse<Perspective> Perspective(@RequestParam(value = "perspectiveType") String perspectiveType) throws InterruptedException {
		System.out.println("before dao");
		Perspective perspective = perpectiveDao.fetchPerspectiveData(perspectiveType);
		System.out.println("query executed");
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode("200");
		baseResponse.setStatusMsg("Success");
		baseResponse.setData(perspective);
		Thread.sleep(3000);
		return baseResponse;

	}

}
