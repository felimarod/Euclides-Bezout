package euclidesbezoutmcd.logica;

public class Division{
    private int dividendo, divisor, cociente, residuo;

    public Division(int dividendo, int divisor) {
        this.dividendo = dividendo;
        this.divisor = divisor;
        this.cociente  = dividendo / divisor;
        this.residuo = dividendo % divisor;  
    }
    
    /**
     * @return the dividendo
     */
    public int getDividendo() {
        return dividendo;
    }

    /**
     * @return the divisor
     */
    public int getDivisor() {
        return divisor;
    }

    /**
     * @return the cociente
     
     */
    public int getCociente() {
        return cociente
;
    }

    /**
     * @return the residuo
     */
    public int getResiduo() {
        return residuo;
    }

    public int[] getDatos(){
        int [] retorno = {dividendo, divisor, cociente, residuo};
        return retorno; 
    }
}
