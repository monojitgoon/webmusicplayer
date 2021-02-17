package de.uniba.dsg.interfaces;

import de.uniba.dsg.models.Song;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@WebService
public interface ChartsApi {

    @WebMethod(operationName = "get-charts")
    @WebResult(name = "artist")
    List<Song> getCharts(@WebParam(name = "artist-id") @XmlElement(required = true) String artistId);
}
