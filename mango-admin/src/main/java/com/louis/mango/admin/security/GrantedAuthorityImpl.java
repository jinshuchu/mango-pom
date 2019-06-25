package com.louis.mango.admin.security;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Description:  身份验证提供者
 * @Author: created by wangkaishuang on 2019-06-24
 */
@Data
public class GrantedAuthorityImpl implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}