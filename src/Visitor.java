import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class Visitor extends SimpLanPlusBaseVisitor<Node>{
    @Override
    public Node visitProg(SimpLanPlusParser.ProgContext ctx) {
        ArrayList<Node> list = new ArrayList<Node>();
        for(SimpLanPlusParser.DecContext dec: ctx.dec()) {
            list.add(visit(dec));
        }
        for(SimpLanPlusParser.StmContext stm: ctx.stm()) {
            list.add(visit(stm));
        }

        //Qua bisogna ritornare un nodo che contiene la lista(o le due liste)
        return null;
    }

    @Override
    public Node visitDec(SimpLanPlusParser.DecContext ctx) {
        System.out.println(ctx.type());

        Node e = new Node();
        return e;
    }
}
