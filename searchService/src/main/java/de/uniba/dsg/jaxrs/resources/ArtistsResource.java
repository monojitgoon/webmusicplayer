package de.uniba.dsg.jaxrs.resources;

import com.google.gson.Gson;
import com.wrapper.spotify.exceptions.BadRequestException;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Artist;
import de.uniba.dsg.SpotifyApi;
import de.uniba.dsg.interfaces.ArtistsApi;
import de.uniba.dsg.jaxrs.exceptions.*;
import de.uniba.dsg.models.ArtistSearchResult;
import de.uniba.dsg.models.ErrorMessage;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author boehneal, lleuschner
 */

@Path("api/artists")
public class ArtistsResource implements ArtistsApi {

    private static final Logger LOG = Logger.getLogger(ArtistsResource.class.getName());

    @Override
    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchArtists(@QueryParam(value = "artist") String artist) {

        try {
            if (artist == null) {
                throw new ClientRequestException(new ErrorMessage("Client error. Nothing to search"));
            }

            List<Artist> foundArtists = SpotifyApi.getInstance().searchArtists(artist)
                    .build().get().getItems();
            
            if (foundArtists == null) {
                throw new BadGateWayException(new ErrorMessage("Spotify error"));
            }
            
            if (foundArtists.isEmpty()) {
                throw new NoContentException(new ErrorMessage("No artists found"));
            }

            ArtistSearchResult result = new ArtistSearchResult(foundArtists.get(0));

            return Response.status(200).entity(result).build();

        } catch (BadRequestException ex) {
            throw new ResourceNotFoundException(new ErrorMessage("Track not found"));
        } catch (IOException | WebApiException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new RemoteApiException(new ErrorMessage(ex.getMessage()));
        }
    }
}
