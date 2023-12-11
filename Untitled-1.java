private static Map<String, Integer> formula() {
    Map<String, Integer> zmienne = new HashMap<String, Integer>();

    if (sym == symbol.L_NAWIAS) {
        pobSymbol();
        zmienne.putAll(formula());
        if (sym == symbol.P_NAWIAS) {
            pobSymbol();
        } else {
            blad(2);
        }
    } else if (sym == symbol.NEGACJA) {
        pobSymbol();
        zmienne.putAll(formula());
    } else {
        switch (sym) {
            case ZMIENNA_ZDANIOWA:
                int wartosc = 0;
                if (!mapaZmiennych.containsKey(nazwa)) {
                    mapaZmiennych.put(nazwa, wartosc);
                } else {
                    mapaZmiennych.put(nazwa, mapaZmiennych.get(nazwa) + 1);
                }
                pobSymbol();
                break;
            case FALSUM:
            case VERUM:
                formulaAtomowa();
                break;
            default:
                blad(1);
        }
        switch (sym) {
            case KONIEC:
                break;
            case P_NAWIAS:
                break;
            case KONIUNKCJA:
                pobSymbol();
                zmienne.putAll(formula());
                break;
            case ALTERNATYWA:
                pobSymbol();
                zmienne.putAll(formula());
                break;
            case IMPLIKACJA:
                pobSymbol();
                zmienne.putAll(formula());
                break;
            case ROWNOWAZNOSC:
                pobSymbol();
                zmienne.putAll(formula());
                break;
            default:
                blad(3);
        }
    }
    return zmienne;
}
