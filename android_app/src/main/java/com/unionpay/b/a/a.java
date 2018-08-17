package com.unionpay.b.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public interface a extends IInterface {

    public static abstract class a extends Binder implements a {

        private static class a implements a {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public String a(int i, String str, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.client3.tsm.ITsmConnection");
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.client3.tsm.ITsmConnection");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public b[] a(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.client3.tsm.ITsmConnection");
                    obtain.writeInt(i);
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    b[] bVarArr = (b[]) obtain2.createTypedArray(b.CREATOR);
                    return bVarArr;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.a;
            }
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.unionpay.client3.tsm.ITsmConnection");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof a)) ? new a(iBinder) : (a) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.unionpay.client3.tsm.ITsmConnection");
                    String a = a(parcel.readInt(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case 2:
                    parcel.enforceInterface("com.unionpay.client3.tsm.ITsmConnection");
                    a(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.unionpay.client3.tsm.ITsmConnection");
                    Parcelable[] a2 = a(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(a2, 1);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.unionpay.client3.tsm.ITsmConnection");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    String a(int i, String str, int i2) throws RemoteException;

    void a(int i, int i2) throws RemoteException;

    b[] a(int i) throws RemoteException;
}
