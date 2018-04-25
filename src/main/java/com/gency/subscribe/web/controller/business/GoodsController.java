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
import com.gency.subscribe.model.business.Goods;
import com.gency.subscribe.model.system.PageBean;
import com.gency.subscribe.service.business.GoodsService;
import com.gency.subscribe.service.business.GoodsTypeService;
@Controller
@RequestMapping(value="/business/goods")
public class GoodsController {
	
	private static final Log log =LogFactory.getLog(GoodsTypeController.class);
	
	@Autowired
	private GoodsService goodsService;

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
				Goods goods = new Goods();
				goods.setIsDelete(new Byte("0"));
				mv.addObject("goodsList",goodsService.selectListPage(pb, goods));
				mv.addObject("pagebean",pb);
				mv.setViewName("business/goods/list");
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
				 mv.addObject("goods",new Goods());
				 mv.setViewName("business/goods/info");
				 mv.addObject("goodsTypeList", goodsTypeService.selectList(null));
				 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}

	@PostMapping(value="/add")
	public ModelAndView add(ModelAndView mv,@Valid Goods goods,BindingResult result){
		try {
			
			if(result.hasErrors()){
				 mv.addObject("goods",goods);
				 mv.setViewName("business/goods/info");
				 mv.addObject("goodsTypeList", goodsTypeService.selectList(null));
			}else{
				goodsService.save(goods);
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
			Goods goods = goodsService.selectById(id);
			
				 mv.addObject("goods",goods);
				 mv.setViewName("business/goods/info");
				 mv.addObject("goodsTypeList", goodsTypeService.selectList(null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}

	
	@PostMapping(value="/update")
	public ModelAndView update(ModelAndView mv,@Valid Goods goods,BindingResult result){
		try {
			
			if(result.hasErrors()){
				 mv.addObject("goods",goods);
				 mv.setViewName("business/goods/info");
				 mv.addObject("goodsTypeList", goodsTypeService.selectList(null));
			}else{
				goodsService.update(goods);
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
			goodsService.delById(id);
			mv.setViewName("redirect:list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
}







