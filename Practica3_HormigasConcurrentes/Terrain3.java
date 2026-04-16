import java.util.concurrent.locks.*;
import java.util.concurrent.*;
//Se rompe el interbloqueo
public class Terrain3 implements Terrain {
    Viewer v;
    ReentrantLock l;
    Condition[][] ocupado;
    private final int size;
    public  Terrain3 (int t, int ants, int movs, String msg) {
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
            //La hormiga se espera 300ms
            //Si se acabó los 300ms (false), la hormiga se despierta sola
            if(!ocupado[dest.x][dest.y].await(300,TimeUnit.MILLISECONDS)){            
                v.chgDir(a);
            }else{
                v.retry(a); //Si fue despertada por signalAll()
            }
            dest=v.dest(a); //Recalculamos el destino
        }
        v.go(a);
        ocupado[origen.x][origen.y].signalAll();
        }finally{
            l.unlock();
        }
        
    }
}
