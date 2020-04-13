package src;

import java.util.HashMap;
import java.util.Stack;

public class VisitorBinder extends AbstractVisitorError {
    private final Stack<HashMap<String, VarDecl>> envs;

    public VisitorBinder() {
        this.envs = new Stack<>();
    }

    @Override
    public void visit(Num n) {
    }

    @Override
    public void visit(Str s) {
    }

    @Override
    public void visit(BinOp b) {
        b.lex.accept(this);
        b.rex.accept(this);
    }

    @Override
    public void visit(Relation r) {
        r.lex.accept(this);
        r.rex.accept(this);
    }

    @Override
    public void visit(Not n) {
        n.ex.accept(this);
    }

    @Override
    public void visit(Print p) {
        p.ex.accept(this);
    }

    @Override
    public void visit(TernOp t) {
        t.ifEx.accept(this);
        t.thenEx.accept(this);
        t.elseEx.accept(this);
    }

    @Override
    public void visit(VarDecl v) {
        v.e.accept(this);
        this.envs.peek().put(v.id, v);
    }

    @Override
    public void visit(Var v) {
        boolean hit = false;

        for (int i = this.envs.size() - 1; i >= 0; i--)
            if (this.envs.get(i).containsKey(v.id)) {
                v.d = this.envs.get(i).get(v.id);
                hit = true;
                break;
            }

        if (!hit) {
            setError("Undefined variable: Var \"" + v.id + "\" is not defined.");
        }
    }

    @Override
    public void visit(Scope s) {
        this.envs.push(new HashMap<>());

        for (VarDecl v : s.vars.values())
            v.accept(this);
        for (Expression e : s.instrs)
            e.accept(this);

        this.envs.pop();
    }

    @Override
    public void visit(While w) {
        this.envs.push(new HashMap<>());

        w.cond.accept(this);
        for (Expression e : w.instrs)
            e.accept(this);

        this.envs.pop();
    }
} // VisitorBinder
