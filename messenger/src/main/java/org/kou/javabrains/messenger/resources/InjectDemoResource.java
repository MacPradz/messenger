package org.kou.javabrains.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	@Path("annoMatrixParam")
	// MatrigParam is almost the same as QueryParam but here you access
	// .../injectdemo/annoMatrixParam;param=value
	public String getParamsUsingMatrixParam(@MatrixParam("param") String matrixParam) {
		return matrixParam;
	}

	@GET
	@Path("annoHeaderParam")
	// to get headers
	public String getParamsUsingHeaderParam(@HeaderParam("customHeader") String header) {
		return header;
	}

	@GET
	@Path("annoCookieParam")
	// to try this in Postman create header with name "Cookie" and as value set
	// i.e. "name=value" which will create cookie "name" with value "value"
	public String getParamsUsingCookieParam(@CookieParam("name") String cookie) {
		return cookie;
	}

	// @FormParam for submitting html forms
}
