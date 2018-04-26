package com.gency.subscribe.web.controller.business;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gency.subscribe.core.util.base.LazyServiceNumberUtil;
import com.gency.subscribe.core.util.base.LazyServiceSendMssage;
import com.gency.subscribe.model.business.AccAdviserBase;
import com.gency.subscribe.model.business.AdviserReceptionUser;
import com.gency.subscribe.model.business.TraOrderBase;
import com.gency.subscribe.service.business.AdviserReceptionUserService;
import com.gency.subscribe.service.business.OrderService;

@Controller
@RequestMapping(value="/web/order")
public class OrderServiceController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private AdviserReceptionUserService adviserReceptionUserService;
	
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
	public Map<String,Object> save(TraOrderBase base,HttpSession session){
		 Map<String,Object> map = new HashMap<>();
		try {
			String valid_num = (String) session.getAttribute("valide_number");
			if(!valid_num.equals(base.getValideCode())){
				map.put("state", "false");
				map.put("value", "验证码错误");
				return map;
			}
			
			//分配置业顾问给用户
			AccAdviserBase adviser = adviserReceptionUserService.getAccAdviserBaseForUser();
			if(adviser == null){
				map.put("state", "false");
				map.put("value", "当前系统无置业顾问可用");
				return map;
			}
			//存储访问记录
			AdviserReceptionUser adviserReceptionUser = new AdviserReceptionUser();
			adviserReceptionUser.setAdviserId(Integer.valueOf(adviser.getId().toString()));
			adviserReceptionUser.setUserTel(base.getUserTel());
			adviserReceptionUser.setUserName(base.getUserName());
			adviserReceptionUserService.save(adviserReceptionUser);
			base.setAdviserId(adviser.getId());
			base.setAdviserName(adviser.getName());
			base.setAdviserPhone(adviser.getPhone());
			//存储记录
			orderService.save(base);
			//给用户发送短信
			LazyServiceSendMssage.sendSMS(base.getUserTel(), new String []{adviser.getName(),adviser.getPhone()}, "245550");
			//给置业顾问发送短信
			LazyServiceSendMssage.sendSMS(adviser.getPhone(), new String []{base.getUserName(),base.getUserPhone(),base.getDateOfVisit()}, "245552");
			map.put("state", "success");
			map.put("value", adviser.getPhone());
			map.put("value2",adviser.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
}
