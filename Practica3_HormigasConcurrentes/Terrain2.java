import java.util.concurrent.locks.*;
//se utiliza un array de condiciones
//Cada casilla tiene su sala de espera, por lo que si una hormiga quiere ir a esa casilla
//Solo se despierta a las que quieren ir a esa casilla
public class Terrain2 implements Terrain {
    Viewer v;
    ReentrantLock l;
    Condition[][] ocupado;
    private final int size;
    public  Terrain2 (int t, int ants, int movs, String msg) {
        v=new Viewer(t,ants,movs,msg);
        l = new ReentrantLock();
        this.size = t;
        ocupado = new Condition[t][t];
        for(int i = 0;i<t;i++){
            for(int j=0;j<t;j++){
                ocupado[i][j] = l.newCondition();
            }
        }
    }
    public void     hi      (int a) {
        l.lock();
        try{
            v.hi(a);
        }finally{
           l.unlock(); 
        }
    }
    public void     bye     (int a) {
        l.lock();
        try{
            Pos actual = v.getPos(a); //Obtenemos dónde se encuentra la hormiga
            v.bye(a);
            ocupado[actual.x][actual.y].signalAll(); //Avisamos solo a los que quieran esta casilla
        }finally{
            l.unlock();
        }
   }
    public void     move    (int a) throws InterruptedException {
        l.lock();
        try{
        v.turn(a);
        Pos dest=v.dest(a);
        Pos origen=v.getPos(a); //Se obtiene la posición inicial
        while (v.occupied(dest)) {
            ocupado[dest.x][dest.y].await();            
            v.retry(a);
            dest=v.dest(a); //Recalculamos por si acaso
        }
        v.go(a);
        ocupado[origen.x][origen.y].signalAll();
        }finally{
            l.unlock();
        }
        
    }
}