package bf.orange.oguest.oguestbackend.guest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LocationDto implements Serializable {
    private final Long id;
    private final String libelle;
    private final String town;
}
