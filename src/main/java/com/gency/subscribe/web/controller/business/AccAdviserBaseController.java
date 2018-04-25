package com.gency.subscribe.web.controller.business;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.gency.subscribe.model.business.AccAdviserBase;
import com.gency.subscribe.model.system.PageBean;
import com.gency.subscribe.service.business.AdviserService;

@Controller
@RequestMapping(value="/business/adviser")
public class AccAdviserBaseController {
	
	
	
	@Autowired
	private AdviserService adviserService;
	
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
				 mv.addObject("adviserList",adviserService.selectListPage(pb, null));
				 mv.addObject("pagebean",pb);
				 mv.setViewName("business/adviser/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	/**
	 * 添加商品类型
	 * @param mv
	 * @return
	 */
	@GetMapping(value="/add")
	public ModelAndView add(ModelAndView mv){
		try {
				 mv.addObject("accAdviserBase",new AccAdviserBase());
				 mv.setViewName("business/adviser/info");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}

	@PostMapping(value="/add")
	public ModelAndView add(ModelAndView mv,@Valid AccAdviserBase accAdviserBase,BindingResult result){
		try {
			
			if(result.hasErrors()){
				 mv.addObject("accAdviserBase",accAdviserBase);
				 mv.setViewName("business/adviser/info");
			}else{
				adviserService.save(accAdviserBase);
				mv.setViewName("redirect:list");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	
	@GetMapping(value="/update")
	public ModelAndView update(ModelAndView mv,Integer id){
		try {
			AccAdviserBase accAdviserBase = adviserService.selectById(id);
			
				 mv.addObject("accAdviserBase",accAdviserBase);
				 mv.setViewName("business/adviser/info");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}

	
	@PostMapping(value="/update")
	public ModelAndView update(ModelAndView mv,@Valid AccAdviserBase accAdviserBase,BindingResult result){
		try {
			
			if(result.hasErrors()){
				 mv.addObject("accAdviserBase",accAdviserBase);
				 mv.setViewName("business/adviser/info");
			}else{
				adviserService.update(accAdviserBase);
				mv.setViewName("redirect:list");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	
	/**
	 * 添加商品类型
	 * @param mv
	 * @return
	 */
	@GetMapping(value="/delete")
	public ModelAndView delete(ModelAndView mv,Integer id){
		try {
			adviserService.delById(id);
			mv.setViewName("redirect:list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	

}
