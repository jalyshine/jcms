package cn.jaly.utils.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 生成加密密码
 */
public class StockHashedCredentialsMatcher extends HashedCredentialsMatcher{

	public String getHashProvidedCredentials(String credentials, String salt){
		String result = null;
		String hashAlgorithmName = getHashAlgorithmName();
		int hashIterations = getHashIterations();
		if(hashAlgorithmName != null){
			result = new SimpleHash(hashAlgorithmName, credentials, salt,
					hashIterations).toString();
		}
		return result;
	}
}
