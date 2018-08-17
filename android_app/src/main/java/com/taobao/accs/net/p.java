package com.taobao.accs.net;

import com.taobao.accs.data.Message;

/* compiled from: Taobao */
class p implements Runnable {
    final /* synthetic */ Message a;
    final /* synthetic */ boolean b;
    final /* synthetic */ o c;

    p(o oVar, Message message, boolean z) {
        this.c = oVar;
        this.a = message;
        this.b = z;
    }

    public void run() {
        synchronized (this.c.q) {
            this.c.a(this.a);
            if (this.c.q.size() == 0) {
                this.c.q.add(this.a);
            } else {
                Message message = (Message) this.c.q.getFirst();
                if (this.a.getType() == 1 || this.a.getType() == 0) {
                    this.c.q.addLast(this.a);
                    if (message.getType() == 2) {
                        this.c.q.removeFirst();
                    }
                } else if (this.a.getType() != 2 || message.getType() != 2) {
                    this.c.q.addLast(this.a);
                } else if (!message.force && this.a.force) {
                    this.c.q.removeFirst();
                    this.c.q.addFirst(this.a);
                }
            }
            if (this.b || this.c.p == 3) {
                try {
                    this.c.q.notifyAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
