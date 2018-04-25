package com.gency.subscribe.web.controller.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.gency.subscribe.model.business.Goods;
import com.gency.subscribe.model.business.SerUserBase;
import com.gency.subscribe.model.business.SocketMessage;
import com.gency.subscribe.service.business.GoodsService;
import com.gency.subscribe.service.business.GoodsTypeService;
import com.gency.subscribe.service.business.SerUserBaseService;

@Controller
@RequestMapping(value="/web/call")
public class CallGoodsContrlloer {

	@Autowired
	private GoodsTypeService goodsTypeService;
	
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	private SerUserBaseService serUserBaseService;
	
	@GetMapping(value="/goodscall")
	public ModelAndView nomocall(ModelAndView mv){
		try {
			
				 mv.addObject("goodsTypeList", goodsTypeService.selectList(null));
				 mv.addObject("goodsList", goodsService.selectList(null));
				 mv.setViewName("web/goodscall");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	@PostMapping(value="/goodspullcall")
	@ResponseBody
	public String nomocall(String tableNumber,String datas){
		try {
			if(StringUtils.isBlank(tableNumber)){
				return "未输入桌号";
			}
			if(StringUtils.isBlank(datas)){
				return "未选择商品";
			}
			String data = datas.substring(1, datas.length());
			String [] goods = data.split("&");
			List<Goods> list = new ArrayList<>();
			for (int i = 0; i < goods.length; i++) {
				String goods_name = goods[i].split(":")[0];
				String goods_number = goods[i].split(":")[1];
				Goods goods2 = new Goods();
				goods2.setName(goods_name);
				goods2.setNum(goods_number);
				list.add(goods2);
			}
			SerUserBase base = new SerUserBase();
			base.setTablesNumber(tableNumber);
			base.setGoodsInfo(data);
			serUserBaseService.save(base);
			 SocketMessage messages = new SocketMessage();
        	 messages.setType(2);
        	 messages.setMessage("有人呼叫");
        	 SocketMessage socketMessage = new SocketMessage();
        	 socketMessage.setType(2);
        	 socketMessage.setGoods(list);
        	 socketMessage.setMessage(tableNumber);
        	 simpMessagingTemplate.convertAndSend("/topic/send", socketMessage);
			return "success";
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return "success";
	}
}
