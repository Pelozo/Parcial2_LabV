package net.pelozo.parcial.leonardo.velozo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Manager extends Person{

    @OneToMany
    @JoinColumn(name = "player_id")
    private List<Player> players;

    @Override
    public TypePerson typePerson() {
        return TypePerson.MANAGER;
    }

    public Float getTotalAmount(){
        return (players == null) ? 0 : players.stream().map(Player::getPesoValue).reduce(0f, Float::sum);
    }

    public Float getMoneyWeight(){
        return (players == null) ? 0 : players.stream().map(Player::getPlayerValue).map(Currency::getAmount).reduce(0f, (total, element) -> total + (element/100) * 0.1f);
    }

}
