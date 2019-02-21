package io.whileaway.apit.shiro;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.service.DeveloperService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class DeveloperShiroRealm extends AuthorizingRealm {

    @Autowired
    private DeveloperService developerService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        Developer userInfo  = (Developer)principals.getPrimaryPrincipal();
        authorizationInfo.addRole("admin");
        authorizationInfo.addStringPermission("create");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        Developer developer = developerService.findByName(username);
//        logger.info("获取到相应的User信息");
        System.out.println(username);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                developer.getDeveloperName(), //用户名
                developer.getDeveloperPass(), //密码
                ByteSource.Util.bytes(developer.getSalt()),//salt=username+salt userInfo.getCredentialsSalt()
                getName()  //realm name
        );

        return authenticationInfo;
    }
}
