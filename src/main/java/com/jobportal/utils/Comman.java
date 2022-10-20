package com.jobportal.utils;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jobportal.entity.UserEntity;
import com.jobportal.repositories.RoleRepository;
import com.jobportal.repositories.UserRepository;
import com.jobportal.repositories.UserRoleRepository;
import com.jobportal.serviceInterface.JwtTokenUtilInterface;

@Component
public class Comman  implements HandlerInterceptor {

	@Autowired
	private JwtTokenUtilInterface tokenUtilInterface;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	RoleRepository roleRepository;

	public final static String CUSTUM_ATTRIBUTE_USER_ID = "X-user-id";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

//		String authHeader = request.getHeader("Authorization");
//
//		String token = authHeader.substring(7);
//
//		final String emailString = tokenUtilInterface.getUsernameFromToken(token);
//
//		UserEntity userEntity = userRepository.findByEmail(emailString);
//		request.setAttribute("X-user-id", userEntity.getId());
//		return HandlerInterceptor.super.preHandle(request, response, handler);

		String authHeader = request.getHeader("Authorization");
		String tokenString = (null != authHeader) ? authHeader.split(" ")[1] : null;

		// ArrayList<String> urlsWithoutHeader = new
		// ArrayList<>(Arrays.asList(ApiUrls.URLS_WITHOUT_HEADER));
		final String requestUrl = request.getRequestURI();

		// if (!urlsWithoutHeader.contains(requestUrl)) {

		if (null != tokenString) {
			final String emailString = tokenUtilInterface.getUsernameFromToken(tokenString);

			UserEntity userEntity = userRepository.findByEmail(emailString);

			if (null != userEntity) {
				// ArrayList<com.jobportal.entity.UserRoleEntity> userRoleEntity =
				// userRoleRepository.getRolesOfUser(userEntity.getId());

				ArrayList<String> roles = new ArrayList<>();
//
//					for (int i = 0; i < userRoleEntity.size(); i++) {
//						roles.add(userRoleEntity.get(i).getRole().getRoleName();
//					}

				request.setAttribute("X-user-roles", roles);
				request.setAttribute("X-user-id", userEntity.getId());
			}

		}
		// }

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}