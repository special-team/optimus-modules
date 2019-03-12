import optimus.TOOLKIT;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Account;
import com.github.ooknight.rubik.optimus.archer.platform.service.SecurityUserService;
import com.github.ooknight.rubik.optimus.client.web.SecurityAutoConfiguration;
import com.github.ooknight.rubik.optimus.kernel.KernelConfiguration;
import com.github.ooknight.rubik.prototype.authority.Scope;
import com.github.ooknight.rubik.prototype.authority.SessionUser;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {KernelConfiguration.class, SecurityAutoConfiguration.class})
@TestPropertySource(value = "classpath:develop.properties")
public class DemoWithSpring {

    @Resource
    private AuthenticationConfiguration configuration;
    @Resource
    private PasswordEncoder encoder;
    @Resource
    private SecurityUserService service;

    @Test
    public void test() throws Exception {
        AuthenticationManager am = configuration.getAuthenticationManager();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("admin", "123456");
        Authentication o = am.authenticate(token);
        assert (o.getPrincipal() instanceof SessionUser);
    }

    @Test
    public void test0() {
        System.out.println(encoder.encode("123456"));
        boolean x = encoder.matches("123456", "$2a$08$MoeTFBDdlM1td3Ldkl09fukG4eEJCVJcTCwbgjX38.IGs3hpEmD0S");
        System.out.println(x);
    }

    @Test
    public void test1() {
        String username = "Z09VcHJZMGZOcmQ5QnRRdFY3QWZVQT09";
        String password = "WkMybXZWcDZld1lCdm45b1BULzRTZz09";
        String timestamp = "1";
        String expect = decrypt_("L2RJcTA3eDU0T0hBcXgzNHVtY3VpRVRKek4yd0p0aHovVjAzK3lrK2kzMD0=");
        if (expect != null && expect.equals(username + password + timestamp)) {
            Account account = service.getAccount(decrypt_(username)).orElseThrow(() -> new UsernameNotFoundException(username));
            if (encoder.matches(decrypt_(password), account.getPassword())) {
                Long uid = account.getId();
                Long rid = account.getRole() == null ? null : account.getRole().getId();
                Long gid = account.getGroup() == null ? null : account.getGroup().getId();
                String x = TOOLKIT.TO_JSON_STRING(Scope.BUILD(uid, rid, gid, false));
                System.out.println(x);
            }
        }
    }

    private String encry(String text) {
        BasicTextEncryptor x = new BasicTextEncryptor();
        x.setPassword("nmAt31fNUzq6pPu1");
        return Base64Utils.encodeToUrlSafeString(x.encrypt(text).getBytes());
    }

    private String decrypt_(String text) {
        String secret = "nmAt31fNUzq6pPu1";
        BasicTextEncryptor x = new BasicTextEncryptor();
        x.setPassword(secret);
        return x.decrypt(new String(Base64Utils.decodeFromUrlSafeString(text)));
    }

    private String encrypt_(String text) {
        String secret = "nmAt31fNUzq6pPu1";
        BasicTextEncryptor x = new BasicTextEncryptor();
        x.setPassword(secret);
        return Base64Utils.encodeToUrlSafeString(x.encrypt(text).getBytes());
    }
}
