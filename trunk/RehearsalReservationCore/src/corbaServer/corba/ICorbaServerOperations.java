package corbaServer.corba;


/**
* corbaServer/corba/ICorbaServerOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from C:/Programacion/TuttoVerdi/RehearsalReservationCore/idl/corbaServer.idl
* domingo 2 de mayo de 2010 13H32' CEST
*/


//Interface for the CORBA Server
public interface ICorbaServerOperations 
{
  corbaServer.corba.corbaServerRehearsalDTO[] getRehearsals () throws corbaServer.corba.DBErrorException;
} // interface ICorbaServerOperations
