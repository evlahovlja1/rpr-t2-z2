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

        if (krajnjaTacka < i.pocetnaTacka || pocetnaTacka > i.krajnjaTacka) return null;

        if (pocetnaTacka > i.pocetnaTacka) {
            ret.pocetnaTacka = pocetnaTacka;
            ret.ukljucenaPocetna = ukljucenaPocetna;
        }
        else if (pocetnaTacka < i.pocetnaTacka) {
            ret.pocetnaTacka = i.pocetnaTacka;
            ret.ukljucenaPocetna = i.ukljucenaPocetna;
        }
        else if (doubleJednaki(pocetnaTacka, i.pocetnaTacka)) {
            if (ukljucenaPocetna && i.ukljucenaPocetna) {
                ret.ukljucenaPocetna = true;
            }
            else {
                ret.ukljucenaPocetna = false;
            }
        }

        if (krajnjaTacka < i.krajnjaTacka) {
            ret.krajnjaTacka = krajnjaTacka;
            ret.ukljucenaKrajnja = ukljucenaKrajnja;
        }
        else if (krajnjaTacka > i.krajnjaTacka) {
            ret.krajnjaTacka = i.krajnjaTacka;
            ret.ukljucenaKrajnja = i.ukljucenaKrajnja;
        }
        else if (doubleJednaki(krajnjaTacka, i.krajnjaTacka)) {
            if (ukljucenaKrajnja && i.ukljucenaKrajnja) {
                ret.ukljucenaKrajnja = true;
            }
            else {
                ret.ukljucenaKrajnja = false;
            }
        }

        return ret;
    }

    public static Interval intersect(Interval i1, Interval i2) {
        Interval ret = new Interval();

        if (i1.krajnjaTacka < i2.pocetnaTacka || i1.pocetnaTacka > i2.krajnjaTacka) return null;

        if (i1.pocetnaTacka > i2.pocetnaTacka) {
            ret.pocetnaTacka = i1.pocetnaTacka;
            ret.ukljucenaPocetna = i1.ukljucenaPocetna;
        }
        else if (i1.pocetnaTacka < i2.pocetnaTacka) {
            ret.pocetnaTacka = i2.pocetnaTacka;
            ret.ukljucenaPocetna = i2.ukljucenaPocetna;
        }
        else if (doubleJednaki(i1.pocetnaTacka, i2.pocetnaTacka)) {
            if (i1.ukljucenaPocetna && i2.ukljucenaPocetna) {
                ret.ukljucenaPocetna = true;
            }
            else {
                ret.ukljucenaPocetna = false;
            }
        }

        if (i1.krajnjaTacka < i2.krajnjaTacka) {
            ret.krajnjaTacka = i1.krajnjaTacka;
            ret.ukljucenaKrajnja = i1.ukljucenaKrajnja;
        }
        else if (i1.krajnjaTacka > i2.krajnjaTacka) {
            ret.krajnjaTacka = i2.krajnjaTacka;
            ret.ukljucenaKrajnja = i2.ukljucenaKrajnja;
        }
        else if (doubleJednaki(i1.krajnjaTacka, i2.krajnjaTacka)) {
            if (i1.ukljucenaKrajnja && i2.ukljucenaKrajnja) {
                ret.ukljucenaKrajnja = true;
            }
            else {
                ret.ukljucenaKrajnja = false;
            }
        }

        return ret;
    }


    @Override
    public String toString() {
        //TODO: += -> = +
        String ret = "";
        if (ukljucenaPocetna) ret += '[';
        else ret += '(';

        ret += pocetnaTacka + ',' + krajnjaTacka;

        if (ukljucenaKrajnja) ret += ']';
        else ret += ')';

        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        Interval i = (Interval) obj;

        return (doubleJednaki(pocetnaTacka, i.pocetnaTacka) &&
                doubleJednaki(krajnjaTacka, i.krajnjaTacka) &&
                ukljucenaPocetna == i.ukljucenaPocetna &&
                ukljucenaKrajnja == i.ukljucenaKrajnja);
    }
}
