package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double pocetnaTacka, krajnjaTacka;
    private boolean ukljucenaPocetna, ukljucenaKrajnja;

    Interval() {
        pocetnaTacka = 0;
        krajnjaTacka = 0;
        ukljucenaKrajnja = false;
        ukljucenaPocetna = false;
    }

    Interval(double _pocetnaTacka, double _krajnjaTacka, boolean _ukljucenaPocetna, boolean _ukljucenaKrajnja) throws IllegalArgumentException {
        if (_pocetnaTacka > _krajnjaTacka) {
            throw new IllegalArgumentException("Pocetna tacka veca od krajnje!");
        }
        pocetnaTacka = _pocetnaTacka;
        krajnjaTacka = _krajnjaTacka;
        ukljucenaKrajnja = _ukljucenaPocetna;
        ukljucenaKrajnja = _ukljucenaKrajnja;
    }

    private static boolean doubleJednaki(double a, double b) {
        final double EPS = 10e-4;
        return Math.abs(a-b) < EPS;
    }

    public boolean isNull() {
        return doubleJednaki(pocetnaTacka, 0.) && doubleJednaki(pocetnaTacka, krajnjaTacka);
    }

    public boolean isIn(double tacka) {
        return tacka >= pocetnaTacka && tacka <= krajnjaTacka;
    }

    public Interval intersect(Interval i) {
        Interval ret = new Interval();

        //iste tacke
        if (doubleJednaki(pocetnaTacka, i.pocetnaTacka) && doubleJednaki(krajnjaTacka, i.krajnjaTacka)) {
            if (ukljucenaPocetna && i.ukljucenaPocetna) {
                ret.ukljucenaPocetna = true;
            }
            else {
                ret.ukljucenaPocetna = false;
            }

            if (ukljucenaKrajnja && i.ukljucenaKrajnja) {
                ret.ukljucenaKrajnja = true;
            }
            else {
                ret.ukljucenaKrajnja = false;
            }
        }
        //samo pocetna tacka ista
        else if (doubleJednaki(pocetnaTacka, i.pocetnaTacka)) {
            if (krajnjaTacka < i.krajnjaTacka) {
                ret.krajnjaTacka = krajnjaTacka;
                ret.ukljucenaKrajnja = ukljucenaKrajnja;
            }
            else {
                ret.krajnjaTacka = i.krajnjaTacka;
                ret.ukljucenaKrajnja = i.ukljucenaKrajnja;
            }
        }
        //samo krajnja tacka ista
        else if (doubleJednaki(krajnjaTacka, i.krajnjaTacka)) {
            if (pocetnaTacka > i.pocetnaTacka) {
                ret.pocetnaTacka = pocetnaTacka;
                ret.ukljucenaPocetna = ukljucenaPocetna;
            }
            else {
                ret.pocetnaTacka = i.pocetnaTacka;
                ret.ukljucenaPocetna = i.ukljucenaPocetna;
            }
        }
        //izvorni interval potpuno unutar i (argumenta metode)


    }

}
