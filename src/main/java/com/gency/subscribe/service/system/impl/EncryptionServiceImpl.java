package com.gency.subscribe.service.system.impl;

import java.security.Key;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha384Hash;
import org.assertj.core.util.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gency.subscribe.core.constant.base.SystemConstant;
import com.gency.subscribe.core.util.encryption.RSAUtils;
import com.gency.subscribe.service.system.EncryptionService;
@Service
public class EncryptionServiceImpl implements EncryptionService{

	@Transactional(readOnly = true)
    public RSAPublicKey generateKey(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        // 已存在密钥
        HttpSession session = request.getSession();
        KeyPair sKeyPair = (KeyPair) session.getAttribute(SystemConstant.HPF_MANAGE_RSA_SESSION_KEY);
        if (sKeyPair != null) {
            return (RSAPublicKey) sKeyPair.getPublic();
        }
        // 生成密钥
        KeyPair keyPair = RSAUtils.generateKeyPair();
        session.setAttribute(SystemConstant.HPF_MANAGE_RSA_SESSION_KEY, keyPair);
        return (RSAPublicKey) keyPair.getPublic();
    }

   
    @Transactional(readOnly = true) 
    public void removePrivateKey(HttpServletRequest request) {
        if (request == null) {
            return;
        }
        HttpSession session = request.getSession();
        session.removeAttribute(SystemConstant.HPF_MANAGE_RSA_SESSION_KEY);
    }

    
    @Transactional(readOnly = true)
    public String decryptParameter(String name, HttpServletRequest request) {
        if (StringUtils.isBlank(name) || request == null) {
            return null;
        }
        HttpSession session = request.getSession();
        KeyPair sKeyPair = (KeyPair) session.getAttribute(SystemConstant.HPF_MANAGE_RSA_SESSION_KEY);
        if (sKeyPair == null) {
            return null;
        }
        RSAPrivateKey privateKey = (RSAPrivateKey) sKeyPair.getPrivate();
        return RSAUtils.decrypt(privateKey, name);
    }


	@Override
	public String encryptBySha384Hash(String text) throws Exception {
		
		return new Sha384Hash(text).toBase64();
	}


	@Override
	public String encryptBase64(String text) throws Exception {
		
		Preconditions.checkArgument(!StringUtils.isEmpty(text), "不能为空"); 
        byte[] bytes = text.getBytes(); 
        return Base64.encodeToString(bytes); 
	}


	@Override
	public String decryptBase64(String text) throws Exception {
		 Preconditions.checkArgument(!StringUtils.isEmpty(text), "消息摘要不能为空"); 
        return Base64.decodeToString(text); 
	}


	@Override
	public String encryptHex(String text) throws Exception {
		Preconditions.checkArgument(!StringUtils.isEmpty(text), "不能为空"); 
        byte[] bytes = text.getBytes(); 
        return Hex.encodeToString(bytes); 
	}


	@Override
	public String decryptHex(String text) throws Exception {
		 Preconditions.checkArgument(!StringUtils.isEmpty(text), "消息摘要不能为空"); 
        return new String(Hex.decode(text)); 
	}

	@Override
	public String getGenerateKey() throws Exception {
		 AesCipherService aesCipherService=new AesCipherService(); 
	        Key key=aesCipherService.generateNewKey(); 
	        return Base64.encodeToString(key.getEncoded()); 
	}


	@Override
	public String getSalt() throws Exception {
		SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator();
		return secureRandomNumberGenerator.nextBytes().toHex();
	}


	@Override
	public String md5Password(String parm1, String parm2, String salt,int iterationCount) throws Exception {
		
		return  new Md5Hash(parm1,parm2+salt,iterationCount).toHex();
	}
}
