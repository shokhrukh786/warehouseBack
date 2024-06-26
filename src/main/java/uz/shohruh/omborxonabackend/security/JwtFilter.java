package uz.shohruh.omborxonabackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.shohruh.omborxonabackend.service.AuthService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthService authService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization!=null && authorization.startsWith("Bearer")){
            authorization = authorization.substring(7);
            String email = jwtProvider.getEmailFromToken(authorization);
            if (email != null){
                /**
                 * email null bo'lmasdan shu yergacha kelsa token buzilmagan,
                 * endi userni sistemaga kergizish uchun shunday email li
                 * userni bazadan olib kelib olishimiz kerak keyin sistemaga uni set qilishimiz kerak
                 */
                UserDetails userDetails = authService.loadUserByUsername(email);
                /**
                 *     userDetails bu sistemadagi user degani
                 *     userDetails ni user sifatida bemalol olsak bo'ladi.
                 */

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                /**
                 * SecurityContextHolder ga userni set qilaman, user tizimga keradi.
                 */
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);

        //SecurityConfig endi buni chaqirib qo'yish kerak.
        //http .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); shunday qilib.

    }
}
