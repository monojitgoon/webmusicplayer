/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uniba.dsg.interfaces;

import de.uniba.dsg.models.Cover;

/**
 *
 * @author boehneal
 */
public interface CoverApi {
    public Cover getUrlFromTrackId(String trackId);
}
