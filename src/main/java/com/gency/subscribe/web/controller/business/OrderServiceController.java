package com.gency.subscribe.web.controller.business;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gency.subscribe.core.util.base.LazyServiceNumberUtil;
import com.gency.subscribe.core.util.base.LazyServiceSendMssage;

@Controller
@RequestMapping(value="/web/order")
public class OrderServiceController {

	
	@GetMapping(value="/show")
	public ModelAndView nomocall(ModelAndView mv){
		try {
				 mv.setViewName("web/order");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	
	/**
	 * 发送短信验证码
	 * @param phone
	 * @return 
	 */
	@PostMapping(value="/sendValide")
	@ResponseBody
	public String sendValide(String phone,HttpSession session){
		try {
			String num = LazyServiceNumberUtil.productNumber();
			session.setAttribute("valide_number", num);
			LazyServiceSendMssage.sendSMS(phone, new String []{num}, "245551");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	
	
	/**
	 * 保存用户预约记录
	 * @param phone
	 * @param session
	 * @return
	 */
	@PostMapping(value="/save")
	@ResponseBody
	public String save(String phone,HttpSession session){
		try {
			String num = LazyServiceNumberUtil.productNumber();
			session.setAttribute("valide_number", num);
			String valid_num = (String) session.getAttribute("valide_number");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	
}
