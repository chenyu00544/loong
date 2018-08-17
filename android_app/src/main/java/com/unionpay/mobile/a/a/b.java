package com.unionpay.mobile.a.a;

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

            public String a(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.mobile.tsm.connect.IRemoteApdu");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.mobile.tsm.connect.IRemoteApdu");
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.mobile.tsm.connect.IRemoteApdu");
                    obtain.writeInt(i);
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(a aVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.mobile.tsm.connect.IRemoteApdu");
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    this.a.transact(4, obtain, obtain2, 0);
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
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.unionpay.mobile.tsm.connect.IRemoteApdu");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof b)) ? new a(iBinder) : (b) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.unionpay.mobile.tsm.connect.IRemoteApdu");
                    String a = a(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case 2:
                    parcel.enforceInterface("com.unionpay.mobile.tsm.connect.IRemoteApdu");
                    a(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.unionpay.mobile.tsm.connect.IRemoteApdu");
                    a();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("com.unionpay.mobile.tsm.connect.IRemoteApdu");
                    a(com.unionpay.mobile.a.a.a.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.unionpay.mobile.tsm.connect.IRemoteApdu");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    String a(String str, int i) throws RemoteException;

    void a() throws RemoteException;

    void a(int i) throws RemoteException;

    void a(a aVar) throws RemoteException;
}
