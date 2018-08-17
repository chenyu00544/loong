package com.unionpay.tsmservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.unionpay.tsmservice.b.aa;
import com.unionpay.tsmservice.b.ac;
import com.unionpay.tsmservice.b.ae;
import com.unionpay.tsmservice.b.ag;
import com.unionpay.tsmservice.b.ai;
import com.unionpay.tsmservice.b.ak;
import com.unionpay.tsmservice.b.am;
import com.unionpay.tsmservice.b.ao;
import com.unionpay.tsmservice.b.aq;
import com.unionpay.tsmservice.b.as;
import com.unionpay.tsmservice.b.au;
import com.unionpay.tsmservice.b.aw;
import com.unionpay.tsmservice.b.ay;
import com.unionpay.tsmservice.b.ba;
import com.unionpay.tsmservice.b.bc;
import com.unionpay.tsmservice.b.be;
import com.unionpay.tsmservice.b.bg;
import com.unionpay.tsmservice.b.bi;
import com.unionpay.tsmservice.b.bk;
import com.unionpay.tsmservice.b.bm;
import com.unionpay.tsmservice.b.bo;
import com.unionpay.tsmservice.b.bq;
import com.unionpay.tsmservice.b.bs;
import com.unionpay.tsmservice.b.bw;
import com.unionpay.tsmservice.b.by;
import com.unionpay.tsmservice.b.c;
import com.unionpay.tsmservice.b.ca;
import com.unionpay.tsmservice.b.cc;
import com.unionpay.tsmservice.b.ce;
import com.unionpay.tsmservice.b.e;
import com.unionpay.tsmservice.b.g;
import com.unionpay.tsmservice.b.i;
import com.unionpay.tsmservice.b.k;
import com.unionpay.tsmservice.b.m;
import com.unionpay.tsmservice.b.o;
import com.unionpay.tsmservice.b.q;
import com.unionpay.tsmservice.b.s;
import com.unionpay.tsmservice.b.u;
import com.unionpay.tsmservice.b.w;
import com.unionpay.tsmservice.b.y;

public interface f extends IInterface {

    public static abstract class a extends Binder implements f {

        private static class a implements f {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public final int a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    this.a.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    obtain.writeInt(i);
                    this.a.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(int i, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    obtain.writeInt(i);
                    if (strArr == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(strArr.length);
                    }
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readStringArray(strArr);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(com.unionpay.tsmservice.b.a aVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (aVar != null) {
                        obtain.writeInt(1);
                        aVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(aa aaVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (aaVar != null) {
                        obtain.writeInt(1);
                        aaVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(ac acVar, d dVar, e eVar) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (acVar != null) {
                        obtain.writeInt(1);
                        acVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (eVar != null) {
                        iBinder = eVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.a.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(ae aeVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (aeVar != null) {
                        obtain.writeInt(1);
                        aeVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(ag agVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (agVar != null) {
                        obtain.writeInt(1);
                        agVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(ai aiVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (aiVar != null) {
                        obtain.writeInt(1);
                        aiVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(ak akVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (akVar != null) {
                        obtain.writeInt(1);
                        akVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(am amVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (amVar != null) {
                        obtain.writeInt(1);
                        amVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(ao aoVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (aoVar != null) {
                        obtain.writeInt(1);
                        aoVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(aq aqVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (aqVar != null) {
                        obtain.writeInt(1);
                        aqVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(as asVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (asVar != null) {
                        obtain.writeInt(1);
                        asVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(au auVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (auVar != null) {
                        obtain.writeInt(1);
                        auVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(aw awVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (awVar != null) {
                        obtain.writeInt(1);
                        awVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(ay ayVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (ayVar != null) {
                        obtain.writeInt(1);
                        ayVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(ba baVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (baVar != null) {
                        obtain.writeInt(1);
                        baVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(bc bcVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (bcVar != null) {
                        obtain.writeInt(1);
                        bcVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(be beVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (beVar != null) {
                        obtain.writeInt(1);
                        beVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(bg bgVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (bgVar != null) {
                        obtain.writeInt(1);
                        bgVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(bi biVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (biVar != null) {
                        obtain.writeInt(1);
                        biVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(bk bkVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (bkVar != null) {
                        obtain.writeInt(1);
                        bkVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(bm bmVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (bmVar != null) {
                        obtain.writeInt(1);
                        bmVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(bo boVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (boVar != null) {
                        obtain.writeInt(1);
                        boVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(bq bqVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (bqVar != null) {
                        obtain.writeInt(1);
                        bqVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(bs bsVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (bsVar != null) {
                        obtain.writeInt(1);
                        bsVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(bw bwVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (bwVar != null) {
                        obtain.writeInt(1);
                        bwVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(bw bwVar, int i, g gVar, c cVar) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (bwVar != null) {
                        obtain.writeInt(1);
                        bwVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(gVar != null ? gVar.asBinder() : null);
                    if (cVar != null) {
                        iBinder = cVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.a.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(by byVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (byVar != null) {
                        obtain.writeInt(1);
                        byVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(c cVar, d dVar, e eVar) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (cVar != null) {
                        obtain.writeInt(1);
                        cVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (eVar != null) {
                        iBinder = eVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.a.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(ca caVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (caVar != null) {
                        obtain.writeInt(1);
                        caVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(cc ccVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (ccVar != null) {
                        obtain.writeInt(1);
                        ccVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(ce ceVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (ceVar != null) {
                        obtain.writeInt(1);
                        ceVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(e eVar, d dVar, e eVar2) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (eVar != null) {
                        obtain.writeInt(1);
                        eVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (eVar2 != null) {
                        iBinder = eVar2.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(g gVar, d dVar, e eVar) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (gVar != null) {
                        obtain.writeInt(1);
                        gVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (eVar != null) {
                        iBinder = eVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(i iVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (iVar != null) {
                        obtain.writeInt(1);
                        iVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(k kVar, d dVar, e eVar) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (kVar != null) {
                        obtain.writeInt(1);
                        kVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (eVar != null) {
                        iBinder = eVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(m mVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (mVar != null) {
                        obtain.writeInt(1);
                        mVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(o oVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (oVar != null) {
                        obtain.writeInt(1);
                        oVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(q qVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (qVar != null) {
                        obtain.writeInt(1);
                        qVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(s sVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (sVar != null) {
                        obtain.writeInt(1);
                        sVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(u uVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (uVar != null) {
                        obtain.writeInt(1);
                        uVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(w wVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (wVar != null) {
                        obtain.writeInt(1);
                        wVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(y yVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (yVar != null) {
                        obtain.writeInt(1);
                        yVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.a.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int a(String str, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    obtain.writeString(str);
                    if (strArr == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(strArr.length);
                    }
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readStringArray(strArr);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.a;
            }
        }

        public static f a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.unionpay.tsmservice.ITsmService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof f)) ? new a(iBinder) : (f) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            bq bqVar = null;
            int a;
            int readInt;
            int readInt2;
            String[] strArr;
            bw bwVar;
            switch (i) {
                case 1:
                    bo boVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        boVar = (bo) bo.CREATOR.createFromParcel(parcel);
                    }
                    a = a(boVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 2:
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    readInt = parcel.readInt();
                    readInt2 = parcel.readInt();
                    if (readInt2 >= 0) {
                        strArr = new String[readInt2];
                    }
                    readInt = a(readInt, strArr);
                    parcel2.writeNoException();
                    parcel2.writeInt(readInt);
                    parcel2.writeStringArray(strArr);
                    return true;
                case 3:
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    String readString = parcel.readString();
                    readInt2 = parcel.readInt();
                    if (readInt2 >= 0) {
                        strArr = new String[readInt2];
                    }
                    readInt = a(readString, strArr);
                    parcel2.writeNoException();
                    parcel2.writeInt(readInt);
                    parcel2.writeStringArray(strArr);
                    return true;
                case 4:
                    aa aaVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        aaVar = (aa) aa.CREATOR.createFromParcel(parcel);
                    }
                    a = a(aaVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 5:
                    be beVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        beVar = (be) be.CREATOR.createFromParcel(parcel);
                    }
                    a = a(beVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 6:
                    ao aoVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        aoVar = (ao) ao.CREATOR.createFromParcel(parcel);
                    }
                    a = a(aoVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 7:
                    bc bcVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        bcVar = (bc) bc.CREATOR.createFromParcel(parcel);
                    }
                    a = a(bcVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 8:
                    ak akVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        akVar = (ak) ak.CREATOR.createFromParcel(parcel);
                    }
                    a = a(akVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 9:
                    am amVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        amVar = (am) am.CREATOR.createFromParcel(parcel);
                    }
                    a = a(amVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 10:
                    ai aiVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        aiVar = (ai) ai.CREATOR.createFromParcel(parcel);
                    }
                    a = a(aiVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 11:
                    bg bgVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        bgVar = (bg) bg.CREATOR.createFromParcel(parcel);
                    }
                    a = a(bgVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 12:
                    i iVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        iVar = (i) i.CREATOR.createFromParcel(parcel);
                    }
                    a = a(iVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 13:
                    k kVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        kVar = (k) k.CREATOR.createFromParcel(parcel);
                    }
                    a = a(kVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()), com.unionpay.tsmservice.e.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 14:
                    g gVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        gVar = (g) g.CREATOR.createFromParcel(parcel);
                    }
                    a = a(gVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()), com.unionpay.tsmservice.e.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 15:
                    e eVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        eVar = (e) e.CREATOR.createFromParcel(parcel);
                    }
                    a = a(eVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()), com.unionpay.tsmservice.e.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 16:
                    m mVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        mVar = (m) m.CREATOR.createFromParcel(parcel);
                    }
                    a = a(mVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 17:
                    o oVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        oVar = (o) o.CREATOR.createFromParcel(parcel);
                    }
                    a = a(oVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 18:
                    ba baVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        baVar = (ba) ba.CREATOR.createFromParcel(parcel);
                    }
                    a = a(baVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 19:
                    y yVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        yVar = (y) y.CREATOR.createFromParcel(parcel);
                    }
                    a = a(yVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 20:
                    bi biVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        biVar = (bi) bi.CREATOR.createFromParcel(parcel);
                    }
                    a = a(biVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 21:
                    ag agVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        agVar = (ag) ag.CREATOR.createFromParcel(parcel);
                    }
                    a = a(agVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 22:
                    ae aeVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        aeVar = (ae) ae.CREATOR.createFromParcel(parcel);
                    }
                    a = a(aeVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 23:
                    as asVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        asVar = (as) as.CREATOR.createFromParcel(parcel);
                    }
                    a = a(asVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 24:
                    ca caVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        caVar = (ca) ca.CREATOR.createFromParcel(parcel);
                    }
                    a = a(caVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 25:
                    aw awVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        awVar = (aw) aw.CREATOR.createFromParcel(parcel);
                    }
                    a = a(awVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 26:
                    bs bsVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        bsVar = (bs) bs.CREATOR.createFromParcel(parcel);
                    }
                    a = a(bsVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 27:
                    by byVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        byVar = (by) by.CREATOR.createFromParcel(parcel);
                    }
                    a = a(byVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 28:
                    w wVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        wVar = (w) w.CREATOR.createFromParcel(parcel);
                    }
                    a = a(wVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 29:
                    bm bmVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        bmVar = (bm) bm.CREATOR.createFromParcel(parcel);
                    }
                    a = a(bmVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 30:
                    ac acVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        acVar = (ac) ac.CREATOR.createFromParcel(parcel);
                    }
                    a = a(acVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()), com.unionpay.tsmservice.e.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 31:
                    aq aqVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        aqVar = (aq) aq.CREATOR.createFromParcel(parcel);
                    }
                    a = a(aqVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 32:
                    s sVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        sVar = (s) s.CREATOR.createFromParcel(parcel);
                    }
                    a = a(sVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 33:
                    cc ccVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        ccVar = (cc) cc.CREATOR.createFromParcel(parcel);
                    }
                    a = a(ccVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 34:
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        bwVar = (bw) bw.CREATOR.createFromParcel(parcel);
                    }
                    a = a(bwVar, parcel.readInt(), com.unionpay.tsmservice.g.a.a(parcel.readStrongBinder()), com.unionpay.tsmservice.c.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 35:
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        bwVar = (bw) bw.CREATOR.createFromParcel(parcel);
                    }
                    a = a(bwVar);
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 36:
                    ay ayVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        ayVar = (ay) ay.CREATOR.createFromParcel(parcel);
                    }
                    a = a(ayVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 37:
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    a = a(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 38:
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    a = a();
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 39:
                    u uVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        uVar = (u) u.CREATOR.createFromParcel(parcel);
                    }
                    a = a(uVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 40:
                    ce ceVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        ceVar = (ce) ce.CREATOR.createFromParcel(parcel);
                    }
                    a = a(ceVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 41:
                    au auVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        auVar = (au) au.CREATOR.createFromParcel(parcel);
                    }
                    a = a(auVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 42:
                    q qVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        qVar = (q) q.CREATOR.createFromParcel(parcel);
                    }
                    a = a(qVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 43:
                    bk bkVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        bkVar = (bk) bk.CREATOR.createFromParcel(parcel);
                    }
                    a = a(bkVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 44:
                    com.unionpay.tsmservice.b.a aVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        aVar = (com.unionpay.tsmservice.b.a) com.unionpay.tsmservice.b.a.CREATOR.createFromParcel(parcel);
                    }
                    a = a(aVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 45:
                    c cVar;
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        cVar = (c) c.CREATOR.createFromParcel(parcel);
                    }
                    a = a(cVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()), com.unionpay.tsmservice.e.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 46:
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmService");
                    if (parcel.readInt() != 0) {
                        bqVar = (bq) bq.CREATOR.createFromParcel(parcel);
                    }
                    a = a(bqVar, com.unionpay.tsmservice.d.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.unionpay.tsmservice.ITsmService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    int a() throws RemoteException;

    int a(int i) throws RemoteException;

    int a(int i, String[] strArr) throws RemoteException;

    int a(com.unionpay.tsmservice.b.a aVar, d dVar) throws RemoteException;

    int a(aa aaVar, d dVar) throws RemoteException;

    int a(ac acVar, d dVar, e eVar) throws RemoteException;

    int a(ae aeVar, d dVar) throws RemoteException;

    int a(ag agVar, d dVar) throws RemoteException;

    int a(ai aiVar, d dVar) throws RemoteException;

    int a(ak akVar, d dVar) throws RemoteException;

    int a(am amVar, d dVar) throws RemoteException;

    int a(ao aoVar, d dVar) throws RemoteException;

    int a(aq aqVar, d dVar) throws RemoteException;

    int a(as asVar, d dVar) throws RemoteException;

    int a(au auVar, d dVar) throws RemoteException;

    int a(aw awVar, d dVar) throws RemoteException;

    int a(ay ayVar, d dVar) throws RemoteException;

    int a(ba baVar, d dVar) throws RemoteException;

    int a(bc bcVar, d dVar) throws RemoteException;

    int a(be beVar, d dVar) throws RemoteException;

    int a(bg bgVar, d dVar) throws RemoteException;

    int a(bi biVar, d dVar) throws RemoteException;

    int a(bk bkVar, d dVar) throws RemoteException;

    int a(bm bmVar, d dVar) throws RemoteException;

    int a(bo boVar, d dVar) throws RemoteException;

    int a(bq bqVar, d dVar) throws RemoteException;

    int a(bs bsVar, d dVar) throws RemoteException;

    int a(bw bwVar) throws RemoteException;

    int a(bw bwVar, int i, g gVar, c cVar) throws RemoteException;

    int a(by byVar, d dVar) throws RemoteException;

    int a(c cVar, d dVar, e eVar) throws RemoteException;

    int a(ca caVar, d dVar) throws RemoteException;

    int a(cc ccVar, d dVar) throws RemoteException;

    int a(ce ceVar, d dVar) throws RemoteException;

    int a(e eVar, d dVar, e eVar2) throws RemoteException;

    int a(g gVar, d dVar, e eVar) throws RemoteException;

    int a(i iVar, d dVar) throws RemoteException;

    int a(k kVar, d dVar, e eVar) throws RemoteException;

    int a(m mVar, d dVar) throws RemoteException;

    int a(o oVar, d dVar) throws RemoteException;

    int a(q qVar, d dVar) throws RemoteException;

    int a(s sVar, d dVar) throws RemoteException;

    int a(u uVar, d dVar) throws RemoteException;

    int a(w wVar, d dVar) throws RemoteException;

    int a(y yVar, d dVar) throws RemoteException;

    int a(String str, String[] strArr) throws RemoteException;
}
