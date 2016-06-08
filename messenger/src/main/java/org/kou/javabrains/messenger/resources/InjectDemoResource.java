package org.kou.javabrains.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/injectdemo") 
@Consumes(MediaType.TEXT_PLAIN) 
@Produces(MediaType.TEXT_PLAIN) 
public class InjectDemoResource {

	@GET
	@Path("annotations")
	//MatrigParam is almost the same as QueryParam but here you access .../injectdemo/annotations;param=value
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam) {
		return matrixParam;
	}
}
