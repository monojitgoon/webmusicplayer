/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uniba.dsg.models;

import com.wrapper.spotify.models.Artist;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lleuschner
 */
@XmlRootElement
public class ArtistSearchResult {

    @XmlAttribute(name = "id")
    private String artistId;
    @XmlAttribute(name = "name")
    private String artistName;

    public ArtistSearchResult() {
    }

    public ArtistSearchResult(String artistId, String artistName) {
        this.artistId = artistId;
        this.artistName = artistName;
    }

    public ArtistSearchResult(Artist artist) {
        this.artistId = artist.getId();
        this.artistName = artist.getName();
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    @Override
    public String toString() {
        return "ArtistSearchResult{" + "artistId=" + artistId + ", artistName=" + artistName + '}';
    }
    
}
