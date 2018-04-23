package com.gency.subscribe.service.system.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gency.subscribe.core.constant.base.SystemConstant;
import com.gency.subscribe.core.util.image.GenerateVerificationCodeUtil;
import com.gency.subscribe.service.system.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

	@Override
	public void getVerificationCode(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		GenerateVerificationCodeUtil.generateVerificationCode(request, response);
	}

	@Override
	public boolean isMatchingVerificationCode(String code,HttpSession session){
		
		return StringUtils.isNotBlank(code) && session != null
                && StringUtils.equalsIgnoreCase(code, (String) session.getAttribute(SystemConstant.VERIFICATION_CODE));
	}

}
