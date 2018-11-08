package ba.unsa.etf.rpr;

public class Bishop extends ChessPiece {
    public Bishop(String position,Color color){
        super(position,color);
    }
    @Override
    public void move(String position){
        this.checkBishop(position);
        super.move(position);
        super.setPosition(position);
    }
    public void checkBishop(String position){
        position=position.toUpperCase();//sve velika zbog tretmana za ista mala,i velika slova;
        String pocetna=super.getPosition().toUpperCase(); //pocetna pozicija figure,u ovom slucaju lovca;


    }
}
