/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uniba.dsg.jaxrs.exceptions;

import de.uniba.dsg.models.ErrorMessage;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author boehneal
 */
public class BadGateWayException extends WebApplicationException {

    public BadGateWayException(ErrorMessage message) {
        super(Response.status(502).entity(message).build());
    }

}
