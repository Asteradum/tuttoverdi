package corbaServer.corba.ICorbaServerPackage;


/**
* corbaServer/corba/ICorbaServerPackage/RehearsalListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from C:/Users/SoNia/workspace/RehearsalReservationCore/idl/corbaServer.idl
* viernes 9 de abril de 2010 17H44' CEST
*/

public final class RehearsalListHolder implements org.omg.CORBA.portable.Streamable
{
  public corbaServer.corba.corbaServerRehearsalDTO value[] = null;

  public RehearsalListHolder ()
  {
  }

  public RehearsalListHolder (corbaServer.corba.corbaServerRehearsalDTO[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = corbaServer.corba.ICorbaServerPackage.RehearsalListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    corbaServer.corba.ICorbaServerPackage.RehearsalListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return corbaServer.corba.ICorbaServerPackage.RehearsalListHelper.type ();
  }

}