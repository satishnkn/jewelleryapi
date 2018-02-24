package com.jewellerypos.api.exceptionmapper;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jewellerypos.api.error.ErrorModel;
import com.jewellerypos.api.error.JPOSErrorConstant;



@Provider
public class CustomValidationExceptionMapper implements
		ExceptionMapper<ValidationException> {



	@Override
	public Response toResponse(ValidationException exception) {
		ErrorModel em = new ErrorModel();
		if (exception instanceof ConstraintViolationException) {

			final ConstraintViolationException constraint = (ConstraintViolationException) exception;
			//final boolean isResponseException = constraint instanceof ResponseConstraintViolationException;

			StringBuilder validation = new StringBuilder();
			for (final ConstraintViolation<?> violation : constraint
					.getConstraintViolations()) {/*
				LOG.log(Level.WARNING, violation.getRootBeanClass()
						.getSimpleName()
						+ "."
						+ violation.getPropertyPath()
						+ ": " + violation.getMessage());*/
				validation.append(violation.getPropertyPath());
				validation.append(" : ");
				validation.append(violation.getMessage());
				validation.append("\n");
			}

			em.setCode(JPOSErrorConstant.BEAN_VALIDATION_ERRORCODE);
			em.setStatus("Bad Request");
			em.setMessage(validation.toString());

			/*if (isResponseException) {
				em.setCode(LeaConstantsUtil.BEAN_VALIDATION_ERRORCODE);
				em.setMessage(exception.getMessage());
				em.setStatus("Bad Response");
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity(em).type(MediaType.APPLICATION_JSON_TYPE)
						.build();
			}*/

			return Response.status(Response.Status.BAD_REQUEST).entity(em)
					.type(MediaType.APPLICATION_JSON_TYPE).build();
		} else {
			em.setCode(JPOSErrorConstant.BEAN_VALIDATION_ERRORCODE);
			em.setMessage(exception.getMessage());
			em.setStatus("Bad Response");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(em).type(MediaType.APPLICATION_JSON_TYPE).build();
		}

		// return
		// Response.status(Status.BAD_REQUEST).entity(em).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

}
