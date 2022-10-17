package com.jobportal.excetpion;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.jobportal.dto.ErrorResponseDto;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

//	@Autowired
//	ErrorLoggerRepository errorLoggerRepository;

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleResourceNotFound(final ResourceNotFoundException exception,
			final HttpServletRequest request) {

		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.callerURL(request.getRequestURI());
		return error;

	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public @ResponseBody ExceptionResponse handleDataIntegrityViolationException(
			final DataIntegrityViolationException exception) {

		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage("Data is present already !!");
		error.callerURL("Data_Redendency");
		return error;

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponseDto handleValidatioinException(final MethodArgumentNotValidException exception) {

		List<String> details = new ArrayList<>();

		for (ObjectError error : exception.getBindingResult().getAllErrors()) {

			details.add(error.getDefaultMessage());

		}

		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage(details.get(0).split("\\*", 2)[0]);
		error.setMsgKey(details.get(0).split("\\*", 2)[1]);
		return error;

	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	public @ResponseBody ErrorResponseDto handleHttpRequestMethodNotSupportedException(
			final HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {

		ErrorResponseDto errorResponseDto = new ErrorResponseDto();
		errorResponseDto.setMessage("Method does not supported ");
		errorResponseDto.setMsgKey("Please check your method type");

		return errorResponseDto;

	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public @ResponseBody ErrorResponseDto handleAccessDeniedException(final AccessDeniedException exception) {

		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage("Access Denied");
		error.setMsgKey("accessDenied");
		return error;

	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponseDto noHandlerFoundException(final NoHandlerFoundException exception) {

		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage("URL not Found, Please check URL");
		error.setMsgKey("URLNotFound");
		return error;

	}
//
//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	public @ResponseBody ErrorResponseDto handleException(final Exception exception, HttpServletRequest request)
//			throws IOException {
//		ErrorLoggerEntity errorRequest = new ErrorLoggerEntity();
////		errorRequest.setBody(request instanceof StandardMultipartHttpServletRequest ? null
////				: request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
//		errorRequest.setHost(InetAddress.getLoopbackAddress().getHostAddress());
//		errorRequest.setMessage(exception.getMessage());
//		errorRequest.setMethod(Enum.valueOf(MethodEnum.class, request.getMethod()));
//		errorRequest.setUrl(request.getRequestURI());
//
//		errorLoggerRepository.save(errorRequest);
//
//		ErrorResponseDto error = new ErrorResponseDto();
//		error.setMessage("Something went wrong. ");
//		error.setMsgKey("something went wrong.");
//		return error;
//
//	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponseDto handledMaxUploadSizeExceededException(
			final MaxUploadSizeExceededException exceededException) {
		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage("Please decrease your file size");
		error.setMsgKey("File size should be below 20MB");
		return error;

	}

	@ExceptionHandler(ExpiredJwtException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public @ResponseBody ErrorResponseDto handleJwtExpiredException(final ExpiredJwtException jwtExpired) {
		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage("JWT expired");
		error.setMsgKey("jwtExpired");
		return error;
	}

}