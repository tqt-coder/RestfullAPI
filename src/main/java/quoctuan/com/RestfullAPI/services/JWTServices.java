package quoctuan.com.RestfullAPI.services;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
public class JWTServices {
    public static final String USER_NAME = "username";
    @Autowired
    private Environment env;

    public String generateTokenLogin(String username) {
        String token = null;
        try {
            JWSSigner jwsSigner = new MACSigner(generateShareSecret());
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();

            builder.claim(USER_NAME, username);
            builder.expirationTime(generateExpirationDate());

            JWTClaimsSet claimsSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            signedJWT.sign(jwsSigner);
            token = signedJWT.serialize();
        } catch (JOSEException e) {
            e.printStackTrace();
        }

        return token;
    }

    private JWTClaimsSet getClaimsFormToken(String token) {
        JWTClaimsSet claims = null;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);

            JWSVerifier verifier = new MACVerifier(generateShareSecret());
            if (signedJWT.verify(verifier)) {
                claims = signedJWT.getJWTClaimsSet();
            }
        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
        }
        return claims;
    }

    private byte[] generateShareSecret() {
        byte[] sharedSecret = new byte[32];
        String sercet_key = env.getProperty("secret_key");

        sharedSecret = sercet_key.getBytes();
        return sharedSecret;
    }

    private Date generateExpirationDate() {
        int expire_time = Integer.parseInt(env.getProperty("expire_time"));

        return new Date(System.currentTimeMillis() + expire_time);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateToken(token);

        return expiration.before(new Date());
    }

    private Date getExpirationDateToken(String token) {
        Date expiration = null;

        JWTClaimsSet claims = getClaimsFormToken(token);
        expiration = claims.getExpirationTime();

        return expiration;
    }

    public String getUserNameFromToken(String token) {
        String username = null;

        JWTClaimsSet claims = getClaimsFormToken(token);
        try {
            username = claims.getStringClaim(USER_NAME);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return username;
    }

    public boolean validateTokenLogin(String token) {
        if (token == null || token.trim().length() == 0) {
            return false;
        }
        String username = getUserNameFromToken(token);
        if(username == null || username.isEmpty()){
            return false;
        }
        if(isTokenExpired(token)){
            return false;
        }

        return true;
    }

}
