package com.gency.subscribe.web.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gency.subscribe.model.system.PageBean;
import com.gency.subscribe.service.business.OrderService;

@Controller
@RequestMapping(value="/business/order")
public class TraOrderBaseContrller {
	
	
	@Autowired
	private OrderService orderService;
	
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述:获取管理员列表
	 * 版本: version 1.0.0
	 * 时间: 2017年8月24日 下午2:45:22
	 * @param pb
	 * @param mv
	 * @return
	 * ModelAndView
	 */
	@GetMapping(value="/list")
	public ModelAndView list(PageBean pb,ModelAndView mv){
		try {
				 mv.addObject("orderList",orderService.selectListPage(pb, null));
				 mv.addObject("pagebean",pb);
				 mv.setViewName("business/order/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	

}
