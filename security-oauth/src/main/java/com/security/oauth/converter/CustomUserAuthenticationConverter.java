package com.security.oauth.converter;

import com.security.user.model.SysUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 优化自org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter
 * jwt返回的principal改为返回SysUser，增加扩展字段
 * @author: stars
 * @date 2020年 07月 10日 13:12
 **/
public class CustomUserAuthenticationConverter implements UserAuthenticationConverter {
    private Collection<? extends GrantedAuthority> defaultAuthorities;

    private UserDetailsService userDetailsService;

    /**
     * Optional {@link UserDetailsService} to use when extracting an {@link Authentication} from the incoming map.
     *
     * @param userDetailsService the userDetailsService to set
     */
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Default value for authorities if an Authentication is being created and the input has no data for authorities.
     * Note that unless this property is set, the default Authentication created by {@link #extractAuthentication(Map)}
     * will be unauthenticated.
     *
     * @param defaultAuthorities the defaultAuthorities to set. Default null.
     */
    public void setDefaultAuthorities(String[] defaultAuthorities) {
        this.defaultAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
                .arrayToCommaDelimitedString(defaultAuthorities));
    }

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put(USERNAME, authentication.getName());
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }

    /**
     * 包含客户端和用户基本信息，但是信息并不全面。
     * @param map 客户端和用户基本信息
     * @return
     */
    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        // 判断map这个集合是否包含user_name这个key值
        if (map.containsKey(USERNAME)) {
            // 取出用户名
            Object principal = map.get(USERNAME);

            Collection<? extends GrantedAuthority> authorities = getAuthorities(map);
            if (userDetailsService != null) {
                UserDetails user = userDetailsService.loadUserByUsername((String) map.get(USERNAME));
                authorities = user.getAuthorities();
                principal = user;
            } else {
                Integer id = (Integer)map.get("id");
                SysUser user = new SysUser();
                user.setUsername((String)principal);
                // map集合中并没有这个id
                // user.setId(Long.valueOf(id));
                principal = user;
            }
            return new UsernamePasswordAuthenticationToken(principal, "N/A", authorities);
        }
        return null;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
        // 判断map集合中是否包含authorities的key值
        if (!map.containsKey(AUTHORITIES)) {
            return defaultAuthorities;
        }
        // 取出authorities 的值
        Object authorities = map.get(AUTHORITIES);
        // 判断authorities 是否属于String类型
        if (authorities instanceof String) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
        }
        if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
                    .collectionToCommaDelimitedString((Collection<?>) authorities));
        }
        throw new IllegalArgumentException("Authorities must be either a String or a Collection");
    }
}
