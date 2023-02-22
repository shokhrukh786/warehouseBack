package uz.shohruh.omborxonabackend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.shohruh.omborxonabackend.entity.Role;

import java.util.Date;

@Component
public class JwtProvider {
    private final static long expireTime = 1000*60*60*24; //1 kun
    private final static String secretKey = "hechkimbilmasin";

    /**
     * role va username orqali token generate qilamiz
     */
    public String generatedToken(String username, Role role){
        Date expireDate = new Date(System.currentTimeMillis() + expireTime);

        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("roles", role.toString())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return token;
    }



    /**
     * tokenni ichidan username ni ololsak buzulmagan bo'ladi(normal holat)
     */
    public String getEmailFromToken(String token){
        try{
            String email = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return email;
        }catch (Exception e){
            return null;
            /**
             * tokenni muddati o'tgan bo'lsa, key xato bo'lsa, ichi buzilgan bo'lsa
             * tipi to'g'ri kelmasligi mumkun shunday holatlar null qaytaraman.
             */
        }
    }
}
