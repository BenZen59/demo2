package com.example.demo2.resource;

import com.example.demo2.dao.DAOFactory;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import metier.Pays;

import java.util.List;

@Path("pays")
@Produces(MediaType.APPLICATION_JSON)
public class PaysResource {
    @GET
    public Response getAll(){
        List<Pays> pays = DAOFactory.getPaysDAO().getAll();
        return Response.ok(new GenericEntity<List<Pays>>(pays){}).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id){
        Pays pays = DAOFactory.getPaysDAO().getByID(id);
        if(pays != null)
            return Response.ok(pays).build();
        return Response.noContent().build();
    }
}
