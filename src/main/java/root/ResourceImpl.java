package root;

import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

public class ResourceImpl implements XAResource {
    private final String indent;

    public ResourceImpl(String _indent) {
        indent = _indent;
    }

    @Override
    public void commit(Xid xid, boolean onePhase) throws XAException {
        System.out.println(indent + " - COMMIT");
    }

    @Override
    public void end(Xid xid, int flags) throws XAException {
        System.out.println(indent + " - END" + parseFlags(flags));
    }

    @Override
    public void forget(Xid xid) throws XAException {
        System.out.println(indent + " - FORGET");
    }

    @Override
    public int getTransactionTimeout() throws XAException {
        return 0;
    }

    @Override
    public boolean isSameRM(XAResource xares) throws XAException {
        return this.equals(xares);
    }

    @Override
    public int prepare(Xid xid) throws XAException {
        System.out.println(indent + " - PREPARE");
        return XA_OK;
    }

    @Override
    public Xid[] recover(int flag) throws XAException {
        return new Xid[0];
    }

    @Override
    public void rollback(Xid xid) throws XAException {
        System.out.println(indent + " - ROLLBACK");
    }

    @Override
    public boolean setTransactionTimeout(int seconds) throws XAException {
        return false;
    }

    @Override
    public void start(Xid xid, int flags) throws XAException {
        System.out.println(indent + " - START" + parseFlags(flags));
    }

    private String parseFlags(int flags) {
        String res = "";

        if ((flags & TMENDRSCAN) == TMENDRSCAN) {
            res += " [TMENDRSCAN]";
        }
        if ((flags & TMFAIL) == TMFAIL) {
            res += " [TMFAIL]";
        }
        if ((flags & TMJOIN) == TMJOIN) {
            res += " [TMJOIN]";
        }
        if ((flags & TMONEPHASE) == TMONEPHASE) {
            res += " [TMONEPHASE]";
        }
        if ((flags & TMRESUME) == TMRESUME) {
            res += " [TMRESUME]";
        }
        if ((flags & TMSTARTRSCAN) == TMSTARTRSCAN) {
            res += " [TMSTARTRSCAN]";
        }
        if ((flags & TMSUCCESS) == TMSUCCESS) {
            res += " [TMSUCCESS]";
        }
        if ((flags & TMSUSPEND) == TMSUSPEND) {
            res += " [TMSUSPEND]";
        }

        return res;
    }
}
