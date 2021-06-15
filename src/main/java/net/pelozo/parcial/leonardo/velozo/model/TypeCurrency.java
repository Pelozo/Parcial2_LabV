package net.pelozo.parcial.leonardo.velozo.model;

public enum TypeCurrency {
    EUR("euros", 111f),
    USD("dolares", 92.67f);

    private String description;
    private float pesoValue;

    TypeCurrency(String description, float pesoValue) {
        this.description = description;
        this.pesoValue = pesoValue;
    }

    public static TypeCurrency find(String value){
        for(TypeCurrency person: values()){
            if(person.toString().equals(value)){
                return person;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid person type: %s", value));
    }

    public String getDescription() {
        return description;
    }

    public Float getPesoValue() {
        return pesoValue;
    }
}
