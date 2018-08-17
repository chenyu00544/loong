package com.baidu.a.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface b extends IInterface {

    public static abstract class a extends Binder implements b {

        private static class a implements b {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public void a(c cVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.mapframework.open.aidl.IMapOpenService");
                    obtain.writeStrongBinder(cVar != null ? cVar.asBinder() : null);
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.a;
            }
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.baidu.mapframework.open.aidl.IMapOpenService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof b)) ? new a(iBinder) : (b) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.baidu.mapframework.open.aidl.IMapOpenService");
                    a(com.baidu.a.a.a.c.a.b(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.baidu.mapframework.open.aidl.IMapOpenService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void a(c cVar) throws RemoteException;
}
