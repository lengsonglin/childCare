package com.cqut.childcare.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * Description: jwt的token生成与解析
 * Author: <a href="https://github.com/zongzibinbin">abin</a>
 * Date: 2023-04-03
 */
@Slf4j
@Component
public class JwtUtils {

    /**
     * token秘钥，请勿泄露，请勿随便修改
     */
    @Value("${childcare.jwt.secret}")
    private String secret;

    private static final String CUSTOMERID_CLAIM = "customerId";
    private static final String CREATE_TIME = "createTime";

    /**
     * JWT生成Token.<br/>
     * <p>
     * JWT构成: header, payload, signature
     */
    public String createToken(Long customerId) {
        // build token
        String token = JWT.create()
                .withClaim(CUSTOMERID_CLAIM, customerId) // 只存一个customerId信息，其他的自己去redis查
                .withClaim(CREATE_TIME, new Date())
                .sign(Algorithm.HMAC256(secret)); // signature
        return token;
    }

    /**
     * 解密Token
     *
     * @param token
     * @return
     */
    public Map<String, Claim> verifyToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims();
        } catch (Exception e) {
            log.error("decode error,token:{}", token, e);
        }
        return null;
    }


    /**
     * 根据Token获取customerId
     *
     * @param token
     * @return customerId
     */
    public Long getCidOrNull(String token) {
        return Optional.ofNullable(verifyToken(token))
                .map(map -> map.get(CUSTOMERID_CLAIM))
                .map(Claim::asLong)
                .orElse(null);
    }

}
