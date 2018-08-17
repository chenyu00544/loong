package cn.a.a.a.a.a.a.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

public interface a extends IInterface {

    public static abstract class a extends Binder implements a {

        private static class a implements a {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public final String a(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeString(str);
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final Map a(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    Map readHashMap = obtain2.readHashMap(getClass().getClassLoader());
                    return readHashMap;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean a() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final byte[] a(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeByteArray(bArr);
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    byte[] createByteArray = obtain2.createByteArray();
                    return createByteArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final byte[] a(byte[] bArr, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str);
                    this.a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    byte[] createByteArray = obtain2.createByteArray();
                    return createByteArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.a;
            }

            public final boolean b() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    this.a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean b(String str) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeString(str);
                    this.a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final byte[] b(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeByteArray(bArr);
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    byte[] createByteArray = obtain2.createByteArray();
                    return createByteArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final String c() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    this.a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final String d() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    this.a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof a)) ? new a(iBinder) : (a) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int i3 = 0;
            byte[] a;
            String a2;
            boolean a3;
            switch (i) {
                case 1:
                    parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    a = a(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(a);
                    return true;
                case 2:
                    parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    a = b(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(a);
                    return true;
                case 3:
                    parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    a2 = a(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(a2);
                    return true;
                case 4:
                    parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    a3 = a();
                    parcel2.writeNoException();
                    if (a3) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 5:
                    parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    a3 = b();
                    parcel2.writeNoException();
                    if (a3) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 6:
                    parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    Map a4 = a(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeMap(a4);
                    return true;
                case 7:
                    parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    a = a(parcel.createByteArray(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(a);
                    return true;
                case 8:
                    parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    a3 = b(parcel.readString());
                    parcel2.writeNoException();
                    if (a3) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 9:
                    parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    a2 = c();
                    parcel2.writeNoException();
                    parcel2.writeString(a2);
                    return true;
                case 10:
                    parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    a2 = d();
                    parcel2.writeNoException();
                    parcel2.writeString(a2);
                    return true;
                case 1598968902:
                    parcel2.writeString("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    String a(String str) throws RemoteException;

    Map a(String str, String str2) throws RemoteException;

    boolean a() throws RemoteException;

    byte[] a(byte[] bArr) throws RemoteException;

    byte[] a(byte[] bArr, String str) throws RemoteException;

    boolean b() throws RemoteException;

    boolean b(String str) throws RemoteException;

    byte[] b(byte[] bArr) throws RemoteException;

    String c() throws RemoteException;

    String d() throws RemoteException;
}
