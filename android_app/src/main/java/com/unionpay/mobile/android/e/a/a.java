package com.unionpay.mobile.android.e.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface a extends IInterface {

    public static abstract class a extends Binder implements a {

        private static class a implements a {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public final String a(String str, String str2, b bVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.mobile.android.hce.service.IHCEBankService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void a(String str, String str2, String str3, b bVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.mobile.android.hce.service.IHCEBankService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.a;
            }
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.unionpay.mobile.android.hce.service.IHCEBankService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof a)) ? new a(iBinder) : (a) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.unionpay.mobile.android.hce.service.IHCEBankService");
                    String a = a(parcel.readString(), parcel.readString(), com.unionpay.mobile.android.e.a.b.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case 2:
                    parcel.enforceInterface("com.unionpay.mobile.android.hce.service.IHCEBankService");
                    a(parcel.readString(), parcel.readString(), parcel.readString(), com.unionpay.mobile.android.e.a.b.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.unionpay.mobile.android.hce.service.IHCEBankService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    String a(String str, String str2, b bVar) throws RemoteException;

    void a(String str, String str2, String str3, b bVar) throws RemoteException;
}
