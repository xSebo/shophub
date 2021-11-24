package com.example.clientproject.service.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtils {

    private static String SECRET_KEY;

    @Value("${jwt.secret_key}")
    private void setSECRET_KEY(String aSECRET_KEY){
        SECRET_KEY = aSECRET_KEY;
    }

    public static void getKey(){
        System.out.println(SECRET_KEY);
    }

    //    https://github.com/oktadev/okta-java-jwt-example/blob/master/src/main/java/com/okta/createverifytokens/JWTDemo.java
    public static String createJWT(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static Claims decodeJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    public static String makeUserJWT(Integer userId, HttpSession session) {
        String jwtId = "loginCred";
        String jwtIssuer = "ShopHub";
        int jwtTimeToLive = 800000;

        String jwt = JWTUtils.createJWT(
                jwtId, // claim = jti
                jwtIssuer, // claim = iss
                userId.toString(), // claim = sub
                jwtTimeToLive // used to calculate expiration (claim = exp)
        );

        return jwt.toString();
    }
}
