package de.uniba.dsg.jaxrs.resources;

import com.wrapper.spotify.exceptions.BadRequestException;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Track;
import de.uniba.dsg.SpotifyApi;
import de.uniba.dsg.interfaces.TracksApi;
import de.uniba.dsg.jaxrs.exceptions.*;
import de.uniba.dsg.models.ErrorMessage;
import de.uniba.dsg.models.TrackSearchResult;

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
@Path("api/tracks")
public class TrackResource implements TracksApi {

    private static final Logger LOG = Logger.getLogger(TrackResource.class.getName());

    @Override
    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchTrack(@QueryParam(value = "title") String title,
            @QueryParam(value = "artist") String artist) {

        try {
            if (title == null && artist == null) {
                throw new ClientRequestException(new ErrorMessage("Client error. Nothing to search"));
            }

            List<Track> foundTracks = SpotifyApi.getInstance().searchTracks(String.join("+", title, artist))
                    .build().get().getItems();

            if (foundTracks == null) {
                throw new BadGateWayException(new ErrorMessage("Spotify error"));
            }

            if (foundTracks.isEmpty()) {
                throw new NoContentException(new ErrorMessage("No tracks found"));
            }

            TrackSearchResult result = new TrackSearchResult(foundTracks.get(0));

            return Response.status(200).entity(result).build();

        } catch (BadRequestException ex) {
            throw new ResourceNotFoundException(new ErrorMessage("Track not found"));
        } catch (IOException | WebApiException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new RemoteApiException(new ErrorMessage(ex.getMessage()));
        }
    }
}
