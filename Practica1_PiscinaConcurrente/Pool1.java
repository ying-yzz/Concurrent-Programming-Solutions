// CSD feb 2015 Juansa Sendra

public class Pool1 extends Pool {   //no kids alone
    //cap = aforo máximo
    //ki = proporción máxima de niños que pueden estar en el agua por instructor
    
    private int kids = 0; //niños nadando
    private int instructors = 0; //instructores nadando
    public synchronized void init(int ki, int cap)           {
        //No se utilizan en Pool1
        this.kids = 0;
        this.instructors = 0;
    }
    public synchronized void kidSwims()   throws InterruptedException  {
        //Si no hay instructores, no se puede entrar
        while(instructors == 0){
            log.waitingToSwim();
            wait();
        }
        //El niño entra
        kids++;
        log.swimming();
    }
    public synchronized void kidRests()  throws InterruptedException    {
        //El niño sale
        kids--;
        log.resting();
        notifyAll(); //Avisa al instructor que espera salir
    }
    public synchronized void instructorSwims() throws InterruptedException  {
        //Instructor entra
        instructors++;
        log.swimming();
        notifyAll(); //Avisa a los niños que estaban esperando instructor
    }
    public synchronized void instructorRests() throws InterruptedException {
        //Un instructor no puede salir si hay niños y solo queda un instructor
        while(instructors == 1 && kids > 0){
            log.waitingToRest();
            wait();
        }
        //El instructor sale
        instructors--;
        log.resting();
    }
}
