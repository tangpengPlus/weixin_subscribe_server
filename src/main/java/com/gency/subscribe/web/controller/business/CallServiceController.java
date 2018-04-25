package com.gency.subscribe.web.controller.business;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gency.subscribe.model.business.SerUserCall;
import com.gency.subscribe.model.business.SocketMessage;
import com.gency.subscribe.service.business.SerUserCallService;
@Controller
@RequestMapping(value="/web/call")
public class CallServiceController {

	@Autowired
	private SerUserCallService serUserCallService;
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@GetMapping(value="/nomocall")
	public ModelAndView nomocall(ModelAndView mv){
		try {
				 mv.setViewName("web/call");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	
	/**
	 * 呼叫
	 * @param tableNumber
	 * @return
	 */
	@PostMapping(value="/pullcall")
	@ResponseBody
	public String nomocall(String tableNumber){
		try {
			if(StringUtils.isBlank(tableNumber)){
				return "未输入桌号";
			}
			SerUserCall call = new SerUserCall();
			call.setTableNumber(tableNumber);
			serUserCallService.save(call);
			 SocketMessage messages = new SocketMessage();
        	 messages.setType(1);
        	 messages.setMessage("有人呼叫");
        	 SocketMessage socketMessage = new SocketMessage();
        	 socketMessage.setType(1);
        	 socketMessage.setMessage(tableNumber+"号桌需要帮助请及时处理");
        	 simpMessagingTemplate.convertAndSend("/topic/send", socketMessage);
			return "success";
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return "success";
	}
}
