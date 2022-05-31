package bf.orange.oguest.oguestbackend.guest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class VisitorDto implements Serializable {
    private final Long id;
    private final String nom;
    private final String prenoms;
    private final String numero_cnib;
    private final String date_validite;
    private final String type_piece;
    private final String statut;
}
