package com.gency.subscribe.service.system;

import java.security.interfaces.RSAPublicKey;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 作者:唐鹏
 * 描述: 加密服务
 * 版本: version 1.0.0
 * 时间: 2017年7月24日 下午2:41:49
 */
public interface EncryptionService {

	
	
	/**
     * 生成密钥(添加私钥至Session并返回公钥)
     * 
     * @param request
     *            httpServletRequest
     * @return 公钥
     */
    RSAPublicKey generateKey(HttpServletRequest request);

    /**
     * 移除私钥
     * 
     * @param request
     *            httpServletRequest
     */
    void removePrivateKey(HttpServletRequest request);

    /**
     * 解密参数
     * 
     * @param name
     *            参数名称
     * @param request
     *            httpServletRequest
     * @return 解密内容
     */
    String decryptParameter(String name, HttpServletRequest request);
    
    /**
     * 
     * 作者:唐鹏
     * 描述: 将数据进行Sha384Hash加密
     * 版本: version 1.0.0
     * 时间: 2017年7月25日 下午12:11:57
     * @param text
     * @return
     * @throws Exception
     * String
     */
    String encryptBySha384Hash(String text)throws Exception;
    
    /**
     * 
     * 作者:唐鹏
     * 描述:将数据进行Base64加密
     * 版本: version 1.0.0
     * 时间: 2017年7月25日 下午2:20:04
     * @param text
     * @return
     * @throws Exception
     * String
     */
    String encryptBase64(String text)throws Exception;
    
    /**
     * 
     * 作者:唐鹏
     * 描述: 将数据进行Base64解密
     * 版本: version 1.0.0
     * 时间: 2017年7月25日 下午2:21:03
     * @param text
     * @return
     * @throws Exception
     * String
     */
    String decryptBase64(String text)throws Exception;
    
    /**
     * 
     * 作者:唐鹏
     * 描述:16进制加密
     * 版本: version 1.0.0
     * 时间: 2017年7月25日 下午2:21:58
     * @param text
     * @return
     * @throws Exception
     * String
     */
    String encryptHex(String text)throws Exception;

    
    /**
     * 
     * 作者:唐鹏
     * 描述:16进制数据解密
     * 版本: version 1.0.0
     * 时间: 2017年7月25日 下午2:22:34
     * @return
     * @throws Exception
     * String
     */
    String decryptHex(String text)throws Exception;
    
    /**
     * 
     * 作者:唐鹏
     * 描述: 获取一个随机token值
     * 版本: version 1.0.0
     * 时间: 2017年7月25日 下午2:33:50
     * @return
     * @throws Exception
     * String
     */
    String getGenerateKey()throws Exception;
    
    /**
     * 
     * 作者:唐鹏
     * 描述: 获取一个加密盐
     * 版本: version 1.0.0
     * 时间: 2017年7月25日 下午2:34:24
     * @return
     * @throws Exception
     * String
     */
    String getSalt()throws Exception;
    
    /**
     * 
     * 作者:唐鹏
     * 描述: MD5加密
     * 版本: version 1.0.0
     * 时间: 2017年8月2日 下午3:05:00
     * @param parm1 加密参数一
     * @param parm2 加密参数二
     * @param salt 加密盐
     * @param iterationCount 迭代次数 最好不要超过三次不然影响性能
     * @return
     * @throws Exception
     * String
     */
    String md5Password(String parm1,String parm2,String salt,int iterationCount)throws Exception;
}
