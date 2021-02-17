package de.uniba.dsg.jaxrs.resources;

import de.uniba.dsg.interfaces.ArtistApi;
import de.uniba.dsg.interfaces.ChartsApi;
import de.uniba.dsg.jaxrs.exceptions.ClientRequestException;
import de.uniba.dsg.models.ErrorMessage;
import de.uniba.dsg.models.Song;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 *
 * @author lleuschner
 */
@Path("api/charts")
public class ChartsResource implements ChartsApi {

    @Override
    @GET
    @Path("{artist-id}")
    public List<Song> getCharts(@PathParam("artist-id") String artistId) {

        if (artistId == null || artistId.isEmpty()) {
            throw new ClientRequestException(new ErrorMessage("ArtistId not provided"));
        }

        ArtistApi artistProxy = new ArtistResource();

        return artistProxy.getTopTracks(artistId);
    }
}
