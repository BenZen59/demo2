package com.example.demo2.resource;

import com.example.demo2.dao.DAOFactory;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import metier.Couleur;

import java.util.List;


// Ce service est accessible à l'url  "/couleurs"
@Path("couleurs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CouleurResource {
    @Operation(summary = "Récupérer une couleur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Couleurs récupéré avec succès"),
            @ApiResponse(responseCode = "404", description = "Couleurs non trouvé")
    })
    // Méthode appellée lors d'une requête HTTP GET
    @GET
    public Response getAll() {
        List<Couleur> couleurs = DAOFactory.getCouleurDAO().getAll();
        return Response.ok(new GenericEntity<List<Couleur>>(couleurs) {
        }).build();
    }

    // Ce service est accessible à l'url  "/couleurs/id"
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id) {

        Couleur couleur = DAOFactory.getCouleurDAO().getByID(id);
        if (couleur != null)
            return Response.ok(couleur).build();
        return Response.noContent().build();

    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, Couleur couleur) {

        if (couleur == null || id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (id != couleur.getId()) {
            return Response.status(Response.Status.CONFLICT).entity(couleur).build();
        }
        if (DAOFactory.getCouleurDAO().update(couleur))
            return Response.ok(couleur).build();
        else
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Couleur couleur) {

        if (couleur == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getCouleurDAO().insert(couleur))
            return Response.ok(couleur).status(Response.Status.CREATED).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Consumes("application/json")
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {

        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getCouleurDAO().delete(new Couleur(id, "")))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
}