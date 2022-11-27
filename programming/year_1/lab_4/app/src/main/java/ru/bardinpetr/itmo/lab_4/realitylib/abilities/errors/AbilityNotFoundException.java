package ru.bardinpetr.itmo.lab_4.realitylib.abilities.errors;

public class AbilityNotFoundException extends RuntimeException {
    public enum SearchType {
        NAME, TYPE
    }

    private final SearchType abilitySearchType;
    private final String abilityRequest;

    public AbilityNotFoundException(SearchType abilitySearchType, String abilityRequest) {
        super("Not found ability \"%s\" searched by %s".formatted(abilityRequest, abilitySearchType.name()));

        this.abilitySearchType = abilitySearchType;
        this.abilityRequest = abilityRequest;
    }

    public SearchType getAbilitySearchType() {
        return abilitySearchType;
    }

    public String getAbilityRequest() {
        return abilityRequest;
    }
}
