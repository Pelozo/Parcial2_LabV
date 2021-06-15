package net.pelozo.parcial.leonardo.velozo.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Player extends Person{

    @DecimalMin(value= "0.0", message = "weight cannot be negative")
    private float weight;

    @Min(value = 0, message = "goals cannot be negative")
    private int goals;

    @Min(value = 0, message = "minutes played cannot be negative")
    private int minutesPlayed;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Currency playerValue;

    private Date birthday;

    @Override
    public TypePerson typePerson() {
        return TypePerson.PLAYER;
    }


    public float getPesoValue(){
        return playerValue.getAmount() * playerValue.getCurrency().getPesoValue();
    }

}
