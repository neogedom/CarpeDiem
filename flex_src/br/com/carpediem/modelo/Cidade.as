package br.com.carpediem.modelo
{
	import org.hamcrest.mxml.collection.InArray;

	[Bindable]
	[RemoteClass(alias="br.com.carpediem.modelo.Cidade")]
	public class Cidade
	{
		public var codigo:int;
		public var nome:String;
		public var estado:Estado;
	}
}