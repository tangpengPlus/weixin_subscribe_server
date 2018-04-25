package com.gency.subscribe.web.controller.business;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gency.subscribe.model.business.GoodsType;
import com.gency.subscribe.model.system.PageBean;
import com.gency.subscribe.service.business.GoodsTypeService;

/**
 * 
 * @author gency
 *
 */
@Controller
@RequestMapping(value="/business/goodstype")
public class GoodsTypeController {
	
	private static final Log log =LogFactory.getLog(GoodsTypeController.class);
	
	
	@Autowired
	private GoodsTypeService goodsTypeService;
	
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
		log.info("获取商品类别列表");
		try {
				GoodsType goodsType = new GoodsType();
				goodsType.setIsDelete(new Byte("0"));
				 mv.addObject("goodsTypeList",goodsTypeService.selectListPage(pb, goodsType));
				 mv.addObject("pagebean",pb);
				 mv.setViewName("business/goodsType/list");
		} catch (Exception e) {
			log.error("获取商品类别列表错误",e);
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
				 mv.addObject("goodsType",new GoodsType());
				 mv.setViewName("business/goodsType/info");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}

	@PostMapping(value="/add")
	public ModelAndView add(ModelAndView mv,@Valid GoodsType goodsType,BindingResult result){
		try {
			
			if(result.hasErrors()){
				 mv.addObject("goodsType",goodsType);
				 mv.setViewName("business/goodsType/info");
			}else{
				goodsTypeService.save(goodsType);
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
			GoodsType goodsType = goodsTypeService.selectById(id);
			
				 mv.addObject("goodsType",goodsType);
				 mv.setViewName("business/goodsType/info");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}

	
	@PostMapping(value="/update")
	public ModelAndView update(ModelAndView mv,@Valid GoodsType goodsType,BindingResult result){
		try {
			
			if(result.hasErrors()){
				 mv.addObject("goodsType",goodsType);
				 mv.setViewName("business/goodsType/info");
			}else{
				goodsTypeService.update(goodsType);
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
			goodsTypeService.delById(id);
			mv.setViewName("redirect:list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
}
