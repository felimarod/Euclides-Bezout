package euclidesbezoutmcd.logica;

public class LibroAE{
    int a, b;
    int [] mcdst;
    int c, c1, c2, d, d1, d2, q, r, r1, r2;
    public LibroAE(int a, int b){
        mcdst = new int [3];
        this.a = a;
        this.b = b;
    }
    public void Calcular(){
        int [] vector;
        vector = EuclidesExtendido(a,b);
        System.out.println("mcd = " + vector[0]);
        System.out.println("s = " + vector[1]);
        System.out.println("t = " + vector[2]);
    }
    private int signo(int x){
        if(x < 0)
            return -1;
        else
            return 1;
    }
    private int [] EuclidesExtendido(int a, int b){
        c = Math.abs(a);    d = Math.abs(b);
        c1 = 1;             c2 = 0;
        d1 = 0;             d2 = 1;
        while (d != 0) {
            q = c / d;
            r = c - q*d;
            r1 = c1 - q*d1;
            r2 = c2 - q*d2;
            c = d ; c1 = d1 ; c2 = d2;
            d = r ; d1 = r1 ; d2 = r2;
        }
        mcdst[0] = Math.abs(c);
        mcdst[1] = c1 * signo(a) * signo(c);
        mcdst[2] = c2;
        return mcdst;
    }

}