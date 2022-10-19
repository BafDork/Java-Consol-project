package game_pack;

import java.util.*;

public class Ellers {

    static final char MAZE_WALL = '0';
    static final char MAZE_PATH = '1';
    static final int  UNDETERMINED = -2;
    static final int  SET_WALL = -1;

    int       rows;           //строки
    int       cols;           //столбцы
    int       act_rows;       //Текущий номер строки
    int       act_cols;       //Текущий номер столбца

    char[][]  feild;          //Поле лабиринта

    int[]     current;        //текущая строка, за исключением наружных стен
    int[]     next;           //следующая строка, за исключением наружных стен

    int       numSet;         //Проверка на совпадение
    private Random fRand;
    private int fNext;
    private int fNext2;

    public Ellers (int nRows, int nCols) {
        act_rows = nRows;
        act_cols = nCols;

        rows = act_rows*2+1;
        cols = act_cols*2+1;

        feild   = new char[rows][cols];
        current = new int[act_cols*2-1];
        next    = new int[act_cols*2-1];

        for(int i =0; i<feild[0].length; i++){
            for(int j=0; j<feild.length; j++){
                feild[i][j] = MAZE_WALL;
            }
        }

        Arrays.fill(next, UNDETERMINED);

        for(int i=0; i<current.length; i+=2){
            current[i] = i/2+1;
            if(i != current.length-1)
                current[i+1] = SET_WALL;
        }
        numSet = current[current.length-1];
    }

    public void makeMaze() {
        setRand(new Random());
        for(int q=0; q<act_rows-1; q++){
            if(q != 0){
                for(int i=0; i<current.length; i++){
                    current[i] = next[i];
                    next[i] = UNDETERMINED;
                    }
            }
            joinSets();
            makeVerticalCuts();

            for(int j=0; j<current.length; j+=2){
                if(next[j] == UNDETERMINED)
                    next[j] = ++numSet;
                if(j != current.length-1)
                    next[j+1] = SET_WALL;
            }

            for(int k=0; k<current.length; k++){
                if(current[k] == SET_WALL){
                    feild[2*q+1][k+1] = MAZE_WALL;
                    feild[2*q+2][k+1] = MAZE_WALL;
                }else{
                    feild[2*q+1][k+1] = MAZE_PATH;
                    if(current[k] == next[k]){
                        feild[2*q+2][k+1] = MAZE_PATH;
                    }
                }

            }
        }

        makeLastRow();
        makeOpenings();

    }

    private void joinSets() {
        Random rand = new Random();

        for(int i=1; i<current.length-1; i+=2){
            if(current[i] == SET_WALL && current[i-1] != current[i+1] && rand.nextBoolean()){
                current[i] = 0;
                int old  = Math.max(current[i-1],current[i+1]);
                fNext= Math.min(current[i-1],current[i+1]);
                for(int j=0; j<current.length; j++){
                    if(current[j] == old)
                        current[j] = fNext;
                }
            }
        }
    }

    private void makeVerticalCuts() {
        Random   rand          = new Random();

        int      begining;     //Начало набора(Включительно)
        int      end;          //Конец набор(Включительно)

        boolean madeVertical;  //Создание вертикального прохода

        int i;
        begining = 0;
        do{
            i=begining;
            while(i<current.length-1 && current[i] == current[i+2]){
                i+=2;
            }
            end = i;

            madeVertical = false;
            do{
                for(int j=begining; j<=end; j+=2){
                    if(rand.nextBoolean()){
                        next[j] = current[j];
                        madeVertical = true;
                    }
                }
            }while(!madeVertical);

            begining = end+2;

        }while(end != current.length-1);
    }

    private void makeLastRow() {
        System.arraycopy(next, 0, current, 0, current.length);

        for(int i=1; i<current.length-1; i+=2){
            if(current[i] == SET_WALL && current[i-1] != current[i+1]){
                current[i] = 0;
                int old  = Math.max(current[i-1],current[i+1]);
                fNext2= Math.min(current[i-1],current[i+1]);
                for(int j=0; j<current.length; j++){
                    if(current[j] == old)
                        current[j] = fNext2;
                }
            }
        }

        for(int k=0; k<current.length; k++){
            if(current[k] == SET_WALL){
                feild[rows-2][k+1] = MAZE_WALL;
            }else{
                feild[rows-2][k+1] = MAZE_PATH;
            }
        }
    }

    public void makeOpenings(){
        Random rand = new Random();
        Random rand2 = new Random();

        int entrance_row = rand.nextInt(act_rows-1) * 2 +1;
        int exit_row = rand2.nextInt(act_rows-1) * 2 +1;

        feild[entrance_row][0] = MAZE_PATH;
        feild[exit_row][cols-1] = MAZE_PATH;

    }

    public void printMaze() {
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                System.out.print(feild[i][j]);
            }
            System.out.println();
        }
    }

    public char[][] getMaze() {
        return feild;
    }

    public Random getRand() {
        return fRand;
    }

    public void setRand(Random rand) {
        fRand = rand;
    }

}
