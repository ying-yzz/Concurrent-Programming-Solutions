// CSD feb 2013 Juansa Sendra

public class Pool4 extends Pool { //kids cannot enter if there are instructors waiting to exit
    private int kids = 0; //niños nadando
    private int instructors = 0; //instructores nadando
    private int maxKidsPerInstructor;
    private int capacity;
    private int waitingIns; //instructores que quieren salir
    
    //cap = aforo máximo
    //ki = proporción máxima de niños que pueden estar en el agua por instructor
    public synchronized void init(int ki, int cap)           {
        this.kids = 0;
        this.instructors = 0;
        this.maxKidsPerInstructor = ki;
        this.capacity = cap;
        this.waitingIns = 0;
    }
    public synchronized void kidSwims()   throws InterruptedException  {
        //Si no hay instructores, no se puede entrar
        while(instructors == 0 ||kids >= instructors * maxKidsPerInstructor || kids+instructors >= capacity || waitingIns>0){
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
        while(kids+instructors >= capacity){
        log.waitingToSwim();
        wait();
        }
        instructors++;
        log.swimming();
        notifyAll(); //Avisa a los niños que estaban esperando entrar
    }
    public synchronized void instructorRests() throws InterruptedException {
        //Un instructor no puede salir si hay niños y solo queda un instructor
        //O si al irse, los niños que quedan superan el ratio del resto de instructores
        waitingIns++;
        while(kids > (instructors -1) * maxKidsPerInstructor){
            log.waitingToRest();
            wait();
        }
        //El instructor sale
        instructors--;
        waitingIns--;
        log.resting();
        notifyAll(); //Notifica a otro instructor que estaba esperando
    }
}
