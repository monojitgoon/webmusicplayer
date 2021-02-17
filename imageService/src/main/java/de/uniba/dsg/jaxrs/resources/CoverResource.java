/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uniba.dsg.jaxrs.resources;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.BadRequestException;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Image;
import com.wrapper.spotify.models.Track;
import de.uniba.dsg.SpotifyApi;
import de.uniba.dsg.jaxrs.exceptions.BadGateWayException;
import de.uniba.dsg.jaxrs.exceptions.ClientRequestException;
import de.uniba.dsg.jaxrs.exceptions.NoContentException;
import de.uniba.dsg.jaxrs.exceptions.RemoteApiException;
import de.uniba.dsg.jaxrs.exceptions.ResourceNotFoundException;
import de.uniba.dsg.models.ErrorMessage;
import de.uniba.dsg.models.Cover;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import de.uniba.dsg.interfaces.CoverApi;

/**
 *
 * @author boehneal
 */
@Path(value = "api/covers")
public class CoverResource implements CoverApi {

    private final Api spotify = SpotifyApi.getInstance();
    private static final Logger LOG = Logger.getLogger(CoverResource.class.getName());

    public CoverResource() {
    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path(value = "{track-id}")
    @Override
    public Cover getUrlFromTrackId(@PathParam(value = "track-id") String trackId) {
        try {
            
            if(trackId == null || trackId.isEmpty()){
                throw new ClientRequestException(new ErrorMessage("Client error. Trackid is missing"));
            }
            
            Track track = spotify.getTrack(trackId).build().get();
            
            if (track == null || track.getAlbum() == null || track.getAlbum().getImages() == null) {
                throw new BadGateWayException(new ErrorMessage("Spotify error"));
            }

            List<Image> images = track.getAlbum().getImages();

            if (images.isEmpty()) {
                throw new NoContentException(new ErrorMessage("No Imageart available"));
            }

            return new Cover(images.get(0).getUrl());

        } catch (BadRequestException ex) {
            throw new ResourceNotFoundException(new ErrorMessage("Track not found"));
        } catch (IOException | WebApiException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new RemoteApiException(new ErrorMessage(ex.getMessage()));
        }
    }

}
