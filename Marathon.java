import java.awt.Image;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Marathon implements GameMode{
    private final Image BG=new ImageIcon("img/BG.png").getImage();
    private final Clip CLIP, WIN, LOSE;
    private int level;
    private Field field;
    private boolean lose;

    public Marathon(){
        this.level=0;
        this.CLIP=Piece.getClip("theme.wav");
        this.WIN=Piece.getClip("win.wav");
        this.LOSE=Piece.getClip("lose.wav");
        this.CLIP.loop(Clip.LOOP_CONTINUOUSLY);
        this.field=new Field(this, new Mino[]{
                new IPiece(),
                new JPiece(),
                new LPiece(),
                new OPiece(),
                new SPiece(),
                new TPiece(),
                new ZPiece()
        });
        this.field.setSpeed(1);
        this.lose=false;
    }
    public void updatePiece(){
        this.level++;
        this.field.setSpeed(this.field.getSpeed()+0.15);
    }
    public void updateLine(){

    }
    public boolean checkWin(){
        return false;
    }
    public boolean checkLose(){
        return this.lose;
    }
    public String[] getScore(){
        return new String[]{"Level: "+this.level};
    }
    public Field getField(){
        return this.field;
    }
    public int compareScore(int a) {
        return this.level-a;
    }
    public void reset(){
        this.field.reset();
        this.lose=false;
        this.level=0;
        this.field.setSpeed(1);
        this.CLIP.setFramePosition(0);
        this.CLIP.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void win(){
        this.CLIP.stop();
        this.WIN.setFramePosition(0);
        this.WIN.start();
    }
    public void lose(){
        this.lose=true;
        this.CLIP.stop();
        this.LOSE.setFramePosition(0);
        this.LOSE.start();
    }
    public void close(){
        this.field.close();
        this.CLIP.close();
        this.WIN.close();
        this.LOSE.close();
    }
    public Image getBG(){
        return BG;
    }
}
