package ChickenInvaders;

public class Cell {

    private Enemy enemy;
    private EnemyType type;
    private int remainingLives;

    private final int row;
    private final int col;

    private final int targetX;
    private final int targetY;

    public Cell(int row,int col,
                int targetX,int targetY,
                EnemyType type,
                int lives){

        this.row=row;
        this.col=col;

        this.targetX=targetX;
        this.targetY=targetY;

        this.type=type;
        this.remainingLives=lives;

        spawnEnemy();
    }

    public void spawnEnemy(){

        switch(type){

            case NORMAL ->
                    enemy=new NormalEnemy(targetX,targetY,1);

            case FAST ->
                    enemy=new FastEnemy(targetX,targetY,1);

            case ZIGZAG ->
                    enemy=new ZigzagEnemy(targetX,targetY,1);

            case SHOOTER ->
                    enemy=new ShooterEnemy(targetX,targetY,1);

        }

    }

    public void enemyKilled(){

        remainingLives--;

        if(remainingLives>0){

            spawnEnemy();

        }else{

            enemy=null;

        }

    }

    public Enemy getEnemy(){

        return enemy;

    }

    public boolean isFinished(){

        return remainingLives<=0;

    }

}