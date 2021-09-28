package ua.training.delivery.constants;

public interface RegularExpressions {


    /**
     * Regex
     */
    String CITY_REGEX_EN = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";
    String CITY_REGEX_UK = "[А-ЩЬЮЯЫҐЄІЇа-щьюяыґєії0-9\\']{2,}";
    String LOGIN_REGEX = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{1,19}$";
    String PASSWORD_REGEX = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії0-9]+";
    String EMAIL_REGEX = "^[^\\s@]+@([^\\s@.,]+\\.)+[^\\s@.,]{2,}$";
    String FIRST_NAME_REGEX = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії\\']{1,}";
    String LAST_NAME_REGEX = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії\\']{2,}";
}
