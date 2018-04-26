package com.gency.subscribe.web.controller.business;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gency.subscribe.model.business.TraOrderBase;
import com.gency.subscribe.service.business.OrderService;

@Controller
@RequestMapping(value="/web/order")
public class VisitController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping(value="/visit")
	public ModelAndView nomocall(ModelAndView mv){
		try {
				 mv.setViewName("web/visit");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	/**
	 *  确认订单
	 * @param telPhone
	 * @return
	 */
	@PostMapping(value="/confirm")
	@ResponseBody
	public Map<String,String> confirm(String telPhone){
		Map<String,String> map = new HashMap<>();
		try {
				//用户预约的订单信息
				TraOrderBase base = orderService.selectUserAd(telPhone);
				if(base == null ){
					map.put("state", "false");
					map.put("value1", "未预定");
					return map;
				}
				//更新用户预约状态
				orderService.updateOrderState(telPhone);
				map.put("state", "success");
				map.put("value1", base.getAdviserName());
				map.put("value2", base.getAdviserPhone());
				return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
}
